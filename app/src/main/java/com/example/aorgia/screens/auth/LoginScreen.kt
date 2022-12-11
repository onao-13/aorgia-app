package com.example.aorgia.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aorgia.app.Screen
import com.example.aorgia.components.MainButton
import com.example.aorgia.components.StartTitle
import com.example.aorgia.ui.theme.AorgiaTheme
import com.example.aorgia.ui.theme.LightRed

@Composable
fun LoginScreen(navController: NavController) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val (text, login, rina) = createRefs()

        StartTitle(
            title = "Войти",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top, margin = 120.dp)
                }
        )

        MainButton(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    launchSingleTop = true
                }
            },
            title = "Для всех",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(login) {
                    bottom.linkTo(rina.top, margin = 40.dp)
                }
        )

        MainButton(
            onClick = {
                navController.navigate(Screen.Rina.route)
            },
            title = "Для Рины",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(rina) {
                    bottom.linkTo(parent.bottom, margin = 40.dp)
                }
        )
    }
}

@Preview
@Composable
fun Preview() {
    AorgiaTheme {
        LoginScreen(navController = rememberNavController())
    }
}