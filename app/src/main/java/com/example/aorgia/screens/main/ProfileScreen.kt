package com.example.aorgia.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.aorgia.components.BottomNavigationPanel
import com.example.aorgia.components.image.UserIconPreview
import com.example.aorgia.data.local.LocalUserInfo
import com.example.aorgia.ui.theme.LightDirtyGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(userData: LocalUserInfo) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
//            val (icon, button) = createRefs()
        Column(Modifier.fillMaxSize()) {

            UserIconPreview(
                size = 250.dp,
                imageLink = userData.icon,
                modifier = Modifier
                    .padding(top = 200.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)
            )

            Text(
                text = userData.name,
                color = LightDirtyGray,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}