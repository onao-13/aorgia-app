package com.example.aorgia.components.modifiers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.aorgia.R

//Set background
@Composable
fun Modifier.setDefaultStarterBackground() = this.then(
    paint(
        painterResource(R.drawable.background_image),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(
            Color.Black.copy(0.84f),
            BlendMode.Darken
        )
    )
)