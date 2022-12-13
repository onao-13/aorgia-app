package com.example.aorgia.components.values

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class ComponentHeight(
    val height: Dp
) {
    object MainHeight55Dp : ComponentHeight(55.dp)
}
