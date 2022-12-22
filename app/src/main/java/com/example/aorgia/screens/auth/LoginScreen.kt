package com.example.aorgia.screens.auth

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.aorgia.api.model.AuthApiViewModel
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.ErrorSnackbar
import com.example.aorgia.components.MainButton
import com.example.aorgia.components.MainTitle
import com.example.aorgia.components.forms.LoginForm
import com.example.aorgia.components.modifiers.setDefaultStarterBackground

@Composable
fun LoginScreen(
    navController: NavHostController,
    login: (email: MutableState<String>, password: MutableState<String>) -> Unit,
    isUserNotFound: MutableState<Boolean> = mutableStateOf(false),
    loading: MutableState<Boolean> = mutableStateOf(false)
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("")}

    val valid = remember(email.value, password.value) {
        email.value.trimEnd().isNotEmpty()
            .and(Patterns.EMAIL_ADDRESS.matcher(email.value).matches())
            && password.value.trimEnd().isNotEmpty()
            .and(password.value.length >= 8)
    }

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .setDefaultStarterBackground()
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
            email, password,
            Modifier
                .fillMaxWidth()
                .constrainAs(form) {
                    top.linkTo(text.bottom, margin = 100.dp)
                }
        )

        MainButton(
            onClick = {
                login(email, password)
            },
            title = "Войти",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(loginButton) {
                    bottom.linkTo(registrationButton.top, margin = 30.dp)
                },
            enabled = valid
        )

        MainButton(
            onClick = {
                navController.navigate(Screen.Registration.route)
            },
            title = "Зарегестрироваться",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(registrationButton) {
                    bottom.linkTo(parent.bottom, margin = 50.dp)
                }
        )

        Box(Modifier.fillMaxSize()) {
            if (loading.value) {
                CircularProgressIndicator(Modifier.align(Center))
            }
            if (isUserNotFound.value) {
                ErrorSnackbar(
                    "Возможно, ты неправильно ввел почту или пароль",
                    Modifier.align(BottomCenter),
                    isUserNotFound
                )
                email.value = ""
                password.value = ""
            }
        }
    }
}