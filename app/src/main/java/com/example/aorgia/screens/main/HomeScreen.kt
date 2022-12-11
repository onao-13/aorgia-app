package com.example.aorgia.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aorgia.ui.theme.AorgiaTheme
import com.example.aorgia.ui.theme.LightRed

@Composable
fun HomeScreen() {
    val money = remember { mutableStateOf(0L) }

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val (moneyCounter, button) = createRefs()

        Text(
            text = money.value.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .constrainAs(moneyCounter) {
                    top.linkTo(parent.top, margin = 60.dp)
                }
        )

        PraySidzu(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentWidth(CenterHorizontally)
                .wrapContentHeight(CenterVertically)
        ) {
            money.value += 10L
        }
    }
}

@Composable
fun PraySidzu(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(200.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightRed,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Молиться \n Сидзу",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun Preview() {
    AorgiaTheme {
        HomeScreen()
    }
}