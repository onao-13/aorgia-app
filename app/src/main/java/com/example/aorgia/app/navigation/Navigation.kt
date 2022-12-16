package com.example.aorgia.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aorgia.api.model.AuthViewModel
import com.example.aorgia.screens.auth.LoginScreen
import com.example.aorgia.screens.auth.RegistrationScreen
import com.example.aorgia.screens.main.HomeScreen
import com.example.aorgia.screens.starter.StartScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController, Screen.Start.route) {
        //auth screens
        composable(Screen.Start.route) { StartScreen(navController) }
        composable(Screen.Login.route) {
            val isUserNotFound = remember { mutableStateOf(true) }

            if (authViewModel.isSuccessfulLogin.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            } else if (authViewModel.isUserNotFound.value) {
                LoginScreen(navController, authViewModel, isUserNotFound)
            } else {
                LoginScreen(navController, authViewModel)
            }
        }
        composable(Screen.Registration.route) { RegistrationScreen(navController) }
        //main
        composable(Screen.Home.route) { HomeScreen() }
    }
}