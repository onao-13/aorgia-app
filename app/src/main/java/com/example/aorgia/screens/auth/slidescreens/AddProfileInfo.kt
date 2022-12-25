package com.example.aorgia.screens.auth.slidescreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
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
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun AddProfileInfo(
    username: MutableState<String>,
    tag: MutableState<String>,
    onClick: () -> Unit
) {
    val valid = remember(username.value, tag.value) {
        username.value.trimEnd().isNotEmpty().and(username.value.length >= 4)
                && tag.value.trimEnd().isNotEmpty().and(tag.value.length >= 4)
    }

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (text, field, buttonNext) = createRefs()

        MainTitle(
            "Придумай себе никнейм" +
                    "и уникальный тег",
            Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top, margin = 80.dp)
                }
        )

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .constrainAs(field) {
                    top.linkTo(text.bottom, margin = 150.dp)
                },
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Column(Modifier.fillMaxWidth()) {
                Text(text = "Никнейм", color = LightDirtyGray)
                MainTextField(username, Modifier.fillMaxWidth())
            }
            Column(Modifier.fillMaxWidth()) {
                Text(text = "Тег", color = LightDirtyGray)
                MainTextField(tag, Modifier.fillMaxWidth())
            }
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
            enabled = valid
        )
    }
}