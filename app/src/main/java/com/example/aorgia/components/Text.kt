package com.example.aorgia.components

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun MainTitle(title: String, modifier: Modifier) {
    Text(
        text = title,
        fontSize = 40.sp,
        fontWeight = FontWeight.SemiBold,
        color = LightDirtyGray,
        modifier = modifier
            .wrapContentWidth(Alignment.CenterHorizontally),
        textAlign = TextAlign.Center,
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = 1.5.em
            )
        )
    )
}