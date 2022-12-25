package com.example.aorgia.app.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentScope.SlideDirection.*
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.aorgia.api.model.AuthApiViewModel
import com.example.aorgia.api.model.ProfileApiViewModel
import com.example.aorgia.app.navigation.Screen.*
import com.example.aorgia.data.local.LocalUser
import com.example.aorgia.database.model.ProfileDbViewModel
import com.example.aorgia.screens.auth.LoginScreen
import com.example.aorgia.screens.auth.RegistrationScreen
import com.example.aorgia.screens.main.HomeScreen
import com.example.aorgia.screens.main.MainScreen
import com.example.aorgia.screens.main.ProfileScreen
import com.example.aorgia.screens.starter.SplashScreen
import com.example.aorgia.screens.starter.StartScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

//TODO: REFACTORING THIS
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
    authApiViewModel: AuthApiViewModel,
    profileDbViewModel: ProfileDbViewModel,
    profileApiViewModel: ProfileApiViewModel
) {
    val animMs = 3000
    val user = authApiViewModel.user
    AnimatedNavHost(navController, Splash.route) {
        //splash
        composable(
            route = Splash.route,
            enterTransition = {
                  when (initialState.destination.route) {
                      else -> slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(100))
                  }
            },
            exitTransition = {
                when(targetState.destination.route) {
                    Home.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(animMs))
                    Start.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(animMs))
                    else -> null
                }
            }
        ) {
            SplashScreen(
                navController,
                profileDbViewModel.profile.collectAsState().value,
                profileApiViewModel
            )
        }
        composable(
            route = Start.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Login.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(animMs))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Login.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(animMs))
                    else -> null
                }
            }
        ) { StartScreen(navController) }
        //auth
        composable(
            route = Login.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Registration.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(animMs))
                    Main.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(animMs))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Registration.route ->
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(animMs))
                    else -> null
                }
            }
        ) {
            val isUserNotFound = authApiViewModel.isUserNotFound
            val loading = authApiViewModel.loading

            when (authApiViewModel.isSuccessful.value) {
                true -> {
                    profileApiViewModel.getProfile(user.value.email)
                    LocalUser().addUserData(profileApiViewModel.profileData.value)
                    profileDbViewModel.login(user.value.email, user.value.password)

                    navController.navigate(Main.route) {
                        popUpTo(Login.route) {
                            inclusive = true
                        }
                    }
                }
                false -> {
                    LoginScreen(
                        navController,
                        authApiViewModel::login,
                        isUserNotFound,
                        loading
                    )
                }
            }
        }
        composable(
            route = Registration.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Main.route ->
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(animMs))
                    else -> null
                }
            }
        ) {
            val isUserExists = authApiViewModel.isUserExist
            val loading = authApiViewModel.loading

            when (authApiViewModel.isSuccessful.value) {
                true -> {
                    Log.d("user", user.value.toString())
                    profileApiViewModel.getProfile(user.value.email)

                    LocalUser().addUserData(profileApiViewModel.profileData.value)

                    navController.navigate(Main.route) {
                        popUpTo(Registration.route) {
                            inclusive = true
                        }
                    }
                }
                false -> {
                    RegistrationScreen(
                        authApiViewModel::checkEmail,
                        profileDbViewModel,
                        isUserExists,
                        loading
                    )
                }
            }
        }
        //main
        composable(Main.route) { MainScreen(navController, LocalUser.Data) }
        composable(
            route = Home.route,
//            enterTransition = {
//                when (initialState.destination.route) {
//                    Profile.route ->
//                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(400))
//                    else -> null
//                }
//            },
//            exitTransition = {
//                when (targetState.destination.route) {
//                    Profile.route ->
//                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(400))
//                    else -> null
//                }
//            }
        ) { HomeScreen() }
        composable(
            route = Profile.route,
//            enterTransition = {
//                when (initialState.destination.route) {
//                    Home.route ->
//                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(400))
//                    else -> null
//                }
//            },
//            exitTransition = {
//                when (targetState.destination.route) {
//                    Home.route ->
//                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(400))
//                    else -> null
//                }
//            }
        ) {
            ProfileScreen(LocalUser.Data)
        }
    }
}