package com.example.aorgia.components.slider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun Slide(
    onClick: () -> Unit,
    screen: @Composable (() -> Unit) -> Unit
) {
    Column(Modifier.width(LocalConfiguration.current.screenWidthDp.dp).fillMaxHeight()) {
        screen(onClick)
    }
}