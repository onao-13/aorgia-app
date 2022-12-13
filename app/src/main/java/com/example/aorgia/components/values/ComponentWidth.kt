package com.example.aorgia.components.values

sealed class ComponentWidth(
    val width: Float
) {
    object MainWidth85f : ComponentWidth(0.85f)
}
