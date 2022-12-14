package com.example.aorgia.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aorgia.screens.auth.LoginScreen
import com.example.aorgia.screens.auth.RegistrationScreen
import com.example.aorgia.screens.main.HomeScreen
import com.example.aorgia.screens.starter.StartScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, Screen.Start.route) {
        //auth screens
        composable(Screen.Start.route) { StartScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Registration.route) { RegistrationScreen(navController) }
        //main
        composable(Screen.Home.route) { HomeScreen() }
    }
}