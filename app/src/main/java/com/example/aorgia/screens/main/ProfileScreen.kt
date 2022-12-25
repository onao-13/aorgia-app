package com.example.aorgia.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.OutlineMainButton
import com.example.aorgia.components.image.UserIconPreview
import com.example.aorgia.data.local.LocalUser
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun ProfileScreen(
    userData: LocalUser.Data,
    navController: NavHostController
) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(Modifier.fillMaxSize()) {
            IconButton(
                onClick = { navController.navigate(Screen.Settings.route) },
                modifier = Modifier.wrapContentWidth(End),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = LightDirtyGray,
                    contentColor = Color.Black
                )
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "setting icon")
            }

            UserIconPreview(
                size = 250.dp,
                imageLink = userData.icon.value,
                modifier = Modifier
                    .padding(top = 160.dp)
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
                onClick = {
                    navController.navigate(Screen.EditProfile.route)
                }
            )
        }
    }
}