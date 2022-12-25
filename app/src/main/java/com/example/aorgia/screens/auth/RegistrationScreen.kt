package com.example.aorgia.screens.auth

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.aorgia.components.modifiers.setDefaultStarterBackground
import com.example.aorgia.components.slider.Slide
import com.example.aorgia.components.slider.HorizontalSlider
import com.example.aorgia.components.slider.SliderScreen
import com.example.aorgia.database.model.ProfileDbViewModel
import com.example.aorgia.screens.auth.slidescreens.AddUserIcon
import com.example.aorgia.screens.auth.slidescreens.AddProfileInfo
import com.example.aorgia.screens.auth.slidescreens.AddAccountInfo

@Composable
fun RegistrationScreen(
    registration: (
        email: MutableState<String>,
        password: MutableState<String>,
        username: MutableState<String>,
        userIcon: MutableState<Uri?>,
        tag: MutableState<String>,
        profileDbViewModel: ProfileDbViewModel
    ) -> Unit,
    profileDbViewModel: ProfileDbViewModel,
    isUserExists: MutableState<Boolean> = mutableStateOf(false),
    loading: MutableState<Boolean> = mutableStateOf(false)
) {
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val userIcon =  remember {
        mutableStateOf<Bitmap?>(null)
    }
    val tag = remember { mutableStateOf("") }
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val sliderScreens = listOf<SliderScreen>(
        SliderScreen {
            Slide(it) { click ->
                AddAccountInfo(password, email, click)
            }
        },
        SliderScreen {
            Slide(it) { click ->
                AddProfileInfo(username, tag, click)
            }
        },
        SliderScreen {
            Slide(it) {
                AddUserIcon(username.value, userIcon, imageUri, loading, isUserExists) {
                    registration(email, password, username, imageUri, tag, profileDbViewModel)
                }
            }
        }
    )

    HorizontalSlider(
        screens = sliderScreens,
        modifier = Modifier
            .fillMaxSize()
            .setDefaultStarterBackground()
    )
}