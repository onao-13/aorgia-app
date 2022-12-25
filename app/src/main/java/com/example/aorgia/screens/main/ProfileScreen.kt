package com.example.aorgia.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aorgia.components.OutlineMainButton
import com.example.aorgia.components.image.UserIconPreview
import com.example.aorgia.data.local.LocalUser
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun ProfileScreen(
    userData: LocalUser.Data,
    onClick: () -> Unit
) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
//            val (icon, button) = createRefs()
        Column(Modifier.fillMaxSize()) {

            UserIconPreview(
                size = 250.dp,
                imageLink = userData.icon.value,
                modifier = Modifier
                    .padding(top = 200.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)
            )

            Text(
                text = userData.username.value,
                color = LightDirtyGray,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = userData.tag.value,
                color = LightDirtyGray,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            OutlineMainButton(
                title = "Изменить профиль",
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally),
                onClick = onClick
            )
        }
    }
}