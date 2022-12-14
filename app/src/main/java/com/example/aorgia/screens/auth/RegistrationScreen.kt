package com.example.aorgia.screens.auth

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.aorgia.app.Screen
import com.example.aorgia.components.slider.Slide
import com.example.aorgia.components.slider.Slider
import com.example.aorgia.components.slider.SliderScreen
import com.example.aorgia.screens.auth.slidescreens.AddUserIcon
import com.example.aorgia.screens.auth.slidescreens.AddUsername
import com.example.aorgia.screens.auth.slidescreens.AddUserInfo

@Composable
fun RegistrationScreen(navController: NavController) {

    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val userIcon =  remember {
        mutableStateOf<Bitmap?>(null)
    }

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
            Slide(it) { click ->
                AddUserIcon(username.value, userIcon) {
                    navController.navigate(Screen.Home.route)
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
}