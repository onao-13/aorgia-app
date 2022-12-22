package com.example.aorgia.screens.starter

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.aorgia.api.model.AuthApiViewModel
import com.example.aorgia.api.model.ProfileApiViewModel
import com.example.aorgia.app.navigation.AppNavigation
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.modifiers.setDefaultStarterBackground
import com.example.aorgia.database.model.ProfileDbViewModel
import com.example.aorgia.ui.theme.LightDirtyGray
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    profileDbViewModel: ProfileDbViewModel
) {
    val scale = remember {
        Animatable(0.4f)
    }

    val user = profileDbViewModel.profile.collectAsState().value

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

        if (user.email.isNotEmpty() && user.password.isNotEmpty()) {
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