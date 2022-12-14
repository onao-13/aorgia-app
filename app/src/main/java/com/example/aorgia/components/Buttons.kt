package com.example.aorgia.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aorgia.components.values.ComponentHeight
import com.example.aorgia.components.values.ComponentWidth
import com.example.aorgia.ui.theme.DisableButtonColor
import com.example.aorgia.ui.theme.DisableButtonTextColor
import com.example.aorgia.ui.theme.LightDirtyGray
import com.example.aorgia.ui.theme.LightRed

@Composable
        /**
         * colors:
         *
         *      container: LightRed
         *      content: LightDirtyGray
         */
fun MainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightRed,
            contentColor = LightDirtyGray,
            disabledContainerColor = DisableButtonColor,
            disabledContentColor = DisableButtonTextColor
        ),
        modifier = modifier
            .fillMaxWidth(ComponentWidth.MainWidth85f.width)
            .height(ComponentHeight.MainHeight55Dp.height),
        shape = RoundedCornerShape(20.dp),
        enabled = enabled
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}