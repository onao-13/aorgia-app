package com.example.aorgia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.aorgia.api.model.AuthApiViewModel
import com.example.aorgia.api.model.ProfileApiViewModel
import com.example.aorgia.app.navigation.AppNavigation
import com.example.aorgia.database.model.ProfileDbViewModel
import com.example.aorgia.ui.theme.AorgiaTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            //Setup Accompanist
            val systemUiController = rememberSystemUiController()
            DisposableEffect(systemUiController, false) {
                systemUiController.setSystemBarsColor(Color.Black, false)

                onDispose {  }
            }

            AorgiaTheme {
                //Library
                val navController = rememberAnimatedNavController()
                val authApiViewModel = viewModel<AuthApiViewModel>()
                val profileDbViewModel = viewModel<ProfileDbViewModel>()
                val profileApiViewModel = viewModel<ProfileApiViewModel>()

                //Navigation
                AppNavigation(
                    navController,
                    authApiViewModel,
                    profileDbViewModel,
                    profileApiViewModel
                )
            }
        }
    }
}