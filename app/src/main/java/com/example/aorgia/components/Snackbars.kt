package com.example.aorgia.components

import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.aorgia.ui.theme.LightDirtyGray
import com.example.aorgia.ui.theme.MainBlack

@Composable
fun ErrorSnackbar(
    errorText: String,
    onDismiss: () -> Unit
) {
    Snackbar(
        containerColor = MainBlack,
        contentColor = LightDirtyGray,
        dismissAction = {
            Button(onClick = onDismiss) {
                Text("Хорошо")
            }
        }
    ) {
        Text(text = errorText)
    }
}