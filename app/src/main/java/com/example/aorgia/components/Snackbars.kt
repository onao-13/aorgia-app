package com.example.aorgia.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.aorgia.ui.theme.DarkRed
import com.example.aorgia.ui.theme.Darkness
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun ErrorSnackbar(
    errorText: String,
    modifier: Modifier,
    show: MutableState<Boolean>
) {
    if (show.value) {
        Snackbar(
            contentColor = LightDirtyGray,
            containerColor = Darkness,
            dismissAction = {
                Button(
                    onClick = { show.value = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkRed,
                        contentColor = LightDirtyGray
                    )
                ) {
                    Text("Хорошо")
                }
            },
            modifier = modifier
        ) {
            Text(text = errorText)
        }
    }
}