package com.example.aorgia.components.image

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

@Composable
fun UserIconImage(
    size: Dp,
    userIcon: Bitmap,
    modifier: Modifier = Modifier
) {
    Image(
        bitmap = userIcon.asImageBitmap(),
        contentDescription = "user icon",
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}