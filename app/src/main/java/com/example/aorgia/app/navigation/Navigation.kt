package com.example.aorgia.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aorgia.api.model.AuthApiViewModel
import com.example.aorgia.screens.auth.LoginScreen
import com.example.aorgia.screens.auth.RegistrationScreen
import com.example.aorgia.screens.main.HomeScreen
import com.example.aorgia.screens.starter.StartScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    authApiViewModel: AuthApiViewModel
) {
    NavHost(navController, Screen.Start.route) {
        //auth screens
        composable(Screen.Start.route) { StartScreen(navController) }
        composable(Screen.Login.route) {
            val isUserNotFound = remember { mutableStateOf(true) }

            if (authApiViewModel.isSuccessfulLogin.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            } else if (authApiViewModel.isUserNotFound.value) {
                LoginScreen(navController, authApiViewModel, isUserNotFound)
            } else {
                LoginScreen(navController, authApiViewModel)
            }
        }
        composable(Screen.Registration.route) {
            val isUserExists = remember { mutableStateOf(true) }

            if (authApiViewModel.isSuccessfulRegistration.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            } else if (authApiViewModel.isUserExists.value) {
                RegistrationScreen(authApiViewModel, isUserExists)
            } else {
                RegistrationScreen(authApiViewModel)
            }
            RegistrationScreen(authApiViewModel)
        }
        //main
        composable(Screen.Home.route) { HomeScreen() }
    }
}