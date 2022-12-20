package com.example.aorgia.screens.starter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aorgia.R
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.MainButton
import com.example.aorgia.components.MainTitle
import com.example.aorgia.components.modifiers.setDefaultStarterBackground
import com.example.aorgia.ui.theme.AorgiaTheme

@Composable
fun StartScreen(navController: NavHostController) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .setDefaultStarterBackground()
    ) {
        val (text, button) = createRefs()

        MainTitle(
            title = "Это \n Аогия",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(text) {
                    top.linkTo(parent.top, margin = 160.dp)
                },
        )

        MainButton(
            onClick = {
                navController.navigate(Screen.Login.route) {
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                },
            title = "Пройти нахуй"
        )
    }
}