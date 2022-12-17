package com.example.aorgia.components

import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun ErrorSnackbar(
    errorText: String,
    modifier: Modifier,
    show: MutableState<Boolean> = mutableStateOf(false)
) {
    if (show.value) {
        Snackbar(
            contentColor = LightDirtyGray,
            dismissAction = {
                Button(onClick = { show.value = false }) {
                    Text("Хорошо")
                }
            },
            modifier = modifier
        ) {
            Text(text = errorText)
        }
    }
}