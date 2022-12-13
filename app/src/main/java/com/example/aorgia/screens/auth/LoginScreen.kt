package com.example.aorgia.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aorgia.app.Screen
import com.example.aorgia.components.MainButton
import com.example.aorgia.components.MainTitle
import com.example.aorgia.components.forms.LoginForm
import com.example.aorgia.ui.theme.AorgiaTheme

@Composable
fun LoginScreen(navController: NavController) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("")}

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val (text, form, loginButton, registrationButton) = createRefs()

        MainTitle(
            title = "Войти в Аогию",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top, margin = 80.dp)
                }
        )

        LoginForm(
            username, password,
            Modifier
                .fillMaxWidth()
                .constrainAs(form) {
                    top.linkTo(text.bottom, margin = 100.dp)
                }
        )

        MainButton(
            onClick = {
                navController.navigate(Screen.Home.route)
            },
            title = "Войти",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(loginButton) {
                    bottom.linkTo(registrationButton.top, margin = 30.dp)
                }
        )
        MainButton(
            onClick = {
                      /*TODO*/
            },
            title = "Зарегестрироваться",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(registrationButton) {
                    bottom.linkTo(parent.bottom, margin = 50.dp)
                }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AorgiaTheme {
        LoginScreen(navController = rememberNavController())
    }
}