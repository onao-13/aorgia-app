package com.example.aorgia.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.MainButton

@Composable
fun SettingsScreen(
    navController: NavHostController,
    logout: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        MainButton(
            onClick = {
                logout()
                navController.navigate(Screen.Start.route) {
                    popUpTo(0)
                }
            },
            title = "Выйти из аккаунта",
            modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
        )
    }
}