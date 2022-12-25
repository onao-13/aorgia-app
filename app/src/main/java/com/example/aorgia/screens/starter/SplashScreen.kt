package com.example.aorgia.screens.starter

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aorgia.api.model.ProfileApiViewModel
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.modifiers.setDefaultStarterBackground
import com.example.aorgia.data.api.AuthUser
import com.example.aorgia.data.local.LocalUser
import com.example.aorgia.ui.theme.LightDirtyGray
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    user: AuthUser,
    profileApiViewModel: ProfileApiViewModel
) {
    val scale = remember {
        Animatable(0.4f)
    }

    profileApiViewModel.getProfile(user.email)

    if (profileApiViewModel.isSuccessful.value) {
        LocalUser().addUserData(profileApiViewModel.profileData.value)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )

        while (profileApiViewModel.loading.value) {
            delay(200L)
        }

        if (LocalUser.Data.tag.value.isNotEmpty()) {
            navController.navigate(Screen.Main.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(Screen.Start.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .setDefaultStarterBackground()
    ) {
        Text(
            text = "Аогия",
            modifier = Modifier
                .align(Alignment.Center)
                .scale(scale.value),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LightDirtyGray
        )
    }
}