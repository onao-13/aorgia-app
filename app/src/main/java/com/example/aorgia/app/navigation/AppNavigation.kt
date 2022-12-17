package com.example.aorgia.app.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aorgia.api.model.AuthApiViewModel
import com.example.aorgia.app.navigation.Screen.*
import com.example.aorgia.database.model.ProfileDbViewModel
import com.example.aorgia.screens.auth.LoginScreen
import com.example.aorgia.screens.auth.RegistrationScreen
import com.example.aorgia.screens.main.HomeScreen
import com.example.aorgia.screens.starter.SplashScreen
import com.example.aorgia.screens.starter.StartScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    authApiViewModel: AuthApiViewModel,
    profileDbViewModel: ProfileDbViewModel
) {

    NavHost(navController, Splash.route) {
        //splash
        composable(Splash.route) { SplashScreen(navController, profileDbViewModel) }
        //auth
        composable(Start.route) { StartScreen(navController) }
        composable(Login.route) {
            val isUserNotFound = authApiViewModel.isUserNotFound
            val loading = authApiViewModel.loading

            if (authApiViewModel.isSuccessfulLogin.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                Log.d("nav", isUserNotFound.value.toString())
                LoginScreen(
                    navController,
                    authApiViewModel::login,
                    isUserNotFound,
                    loading
                )
            }
        }
        composable(Registration.route) {
            val isUserExists = remember { mutableStateOf(true) }

            if (authApiViewModel.isSuccessfulRegistration.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                RegistrationScreen(
                    authApiViewModel,
                    isUserExists,
                    profileDbViewModel
                )
            }
        }
        //main
        composable(Home.route) { HomeScreen() }
    }
}