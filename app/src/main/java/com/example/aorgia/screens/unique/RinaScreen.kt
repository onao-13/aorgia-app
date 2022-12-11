package com.example.aorgia.screens.unique

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.aorgia.ui.theme.AorgiaTheme

@Composable
fun RinaScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            text = "Рина лох",
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun Preview() {
    AorgiaTheme {
        RinaScreen()
    }
}