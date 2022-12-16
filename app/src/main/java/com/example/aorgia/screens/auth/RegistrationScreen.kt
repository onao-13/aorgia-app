package com.example.aorgia.screens.auth

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.aorgia.api.model.AuthViewModel
import com.example.aorgia.components.ErrorSnackbar
import com.example.aorgia.components.slider.Slide
import com.example.aorgia.components.slider.Slider
import com.example.aorgia.components.slider.SliderScreen
import com.example.aorgia.screens.auth.slidescreens.AddUserIcon
import com.example.aorgia.screens.auth.slidescreens.AddUsername
import com.example.aorgia.screens.auth.slidescreens.AddUserInfo
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

@Composable
fun RegistrationScreen(
    authViewModel: AuthViewModel,
    isUserExists: MutableState<Boolean> = mutableStateOf(false)
) {
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val userIcon =  remember {
        mutableStateOf<Bitmap?>(null)
    }
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val sliderScreens = listOf<SliderScreen>(
        SliderScreen {
            Slide(it) { click ->
                AddUserInfo(password, email, click)
            }
        },
        SliderScreen {
            Slide(it) { click ->
                AddUsername(username, click)
            }
        },
        SliderScreen {
            Slide(it) {
                AddUserIcon(username.value, userIcon, imageUri) {
                    createAccount(imageUri, authViewModel, email, password, username)
                }
            }
        }
    )

    Slider(
        screens = sliderScreens,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )

    if (isUserExists.value) {
        Box(Modifier.fillMaxSize()) {
            ErrorSnackbar(
                "Пользователь с такой почтой \n" +
                        "существует",
                Modifier.align(Alignment.BottomCenter)
            ) {
                isUserExists.value = false
            }
        }
    }
}

/**
 * TODO: RELOCATION THIS FUN
 */
fun createAccount(
    imageUri: MutableState<Uri?>,
    authViewModel: AuthViewModel,
    email: MutableState<String>,
    password: MutableState<String>,
    username: MutableState<String>
) {
    val ref = Firebase.storage("gs://aorgia.appspot.com")
        .reference.child("user-icon/" + UUID.randomUUID().toString())
    ref.putFile(imageUri.value!!).continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        ref.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val link = "https://firebasestorage.googleapis.com${task.result.path.toString()}"
            authViewModel.registration(
                email, password, username, link
            )
        }
    }
}