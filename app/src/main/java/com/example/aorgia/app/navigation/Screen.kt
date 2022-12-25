package com.example.aorgia.app.navigation

sealed class Screen(var route: String) {
    object Splash : Screen("splash")
    object Start : Screen("start")
    object Login : Screen("login")
    object Registration : Screen("registration")
    object Main : Screen("main")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object EditProfile : Screen("edit profile")
}