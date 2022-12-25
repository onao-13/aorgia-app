package com.example.aorgia.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.BottomNavigationPanel
import com.example.aorgia.data.local.LocalUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    userData: LocalUser.Data
) {
    val selectedScreen = remember { mutableStateOf(Screen.Home.route) }
    Scaffold(
        bottomBar = {
            BottomNavigationPanel(userData, selectedScreen.value) { screen ->
                selectedScreen.value = screen
            }
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (selectedScreen.value) {
                Screen.Home.route -> HomeScreen()
                Screen.Profile.route -> ProfileScreen(userData, navController)
            }
        }
    }
}