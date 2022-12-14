package com.example.aorgia.components.slider

import androidx.compose.runtime.Composable

data class SliderScreen(
    val screen: @Composable (() -> Unit) -> Unit
)
