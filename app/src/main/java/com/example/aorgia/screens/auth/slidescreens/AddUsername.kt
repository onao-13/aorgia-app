package com.example.aorgia.screens.auth.slidescreens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aorgia.components.MainButton
import com.example.aorgia.components.MainTextField
import com.example.aorgia.components.MainTitle
import com.example.aorgia.ui.theme.AorgiaTheme

@Composable
fun AddUsername(
    username: MutableState<String>,
    onClick: () -> Unit
) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (text, field, buttonNext) = createRefs()

        MainTitle(
            "Придумай себе никнейм",
            Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top, margin = 80.dp)
                }
        )

        Box(
            Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .constrainAs(field) {
                    top.linkTo(text.bottom, margin = 150.dp)
                }
        ) {
            MainTextField(
                inputText = username,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        MainButton(
            onClick = onClick,
            title = "Продолжить",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(buttonNext) {
                    bottom.linkTo(parent.bottom, margin = 50.dp)
                },
            enabled = username.value.trimEnd().isNotEmpty().and(username.value.length >= 4)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AorgiaTheme {
        val username = remember { mutableStateOf("") }
        AddUsername(username) {

        }
    }
}