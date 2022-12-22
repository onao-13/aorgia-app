package com.example.aorgia.screens.auth.slidescreens

import android.util.Patterns
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aorgia.components.MainButton
import com.example.aorgia.components.MainTitle
import com.example.aorgia.components.forms.RegistrationForm
import java.util.regex.Pattern

@Composable
fun AddUserInfo(
    password: MutableState<String>,
    email: MutableState<String>,
    onClick: () -> Unit
) {
   val valid = remember(password.value, email.value) {
       password.value.trimEnd().isNotEmpty()
           .and(password.value.length >= 8)
       && email.value.trimEnd().isNotEmpty()
           .and(Patterns.EMAIL_ADDRESS.matcher(email.value).matches())
   }

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (text, form, registrationButton) = createRefs()

        MainTitle(
            title = "Зарегестрироватся \n в Аогии",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top, margin = 80.dp)
                }
        )

        RegistrationForm(
            password,
            email,
            Modifier
                .fillMaxWidth()
                .constrainAs(form) {
                    top.linkTo(text.bottom, margin = 100.dp)
                }
        )

        MainButton(
            onClick = onClick,
            title = "Продолжить",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .constrainAs(registrationButton) {
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                },
            enabled = valid
        )
    }
}