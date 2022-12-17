package com.example.aorgia.app.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Start : Screen("start")
    object Login : Screen("login")
    object Registration : Screen("registration")
    object Home : Screen("home")
}