package com.example.aorgia.components.forms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aorgia.components.MainTextField
import com.example.aorgia.components.values.ComponentWidth
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun LoginForm(
    username: MutableState<String>,
    password: MutableState<String>,
    modifier: Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth(ComponentWidth.MainWidth85f.width),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthLabelAndForm(username, "Почта")
            AuthLabelAndForm(password, "Пароль")
        }
    }
}

@Composable
fun RegistrationForm(
    password: MutableState<String>,
    email: MutableState<String>,
    modifier: Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth(ComponentWidth.MainWidth85f.width),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthLabelAndForm(email, "Почта")
            AuthLabelAndForm(password, "Пароль")
        }
    }
}

@Composable
private fun AuthLabelAndForm(
    inputText: MutableState<String>,
    label: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextLabel(label)
        MainTextField(
            inputText = inputText,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun TextLabel(
    label: String,
) {
    Text(
        text = label,
        color = LightDirtyGray,
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal
    )
}