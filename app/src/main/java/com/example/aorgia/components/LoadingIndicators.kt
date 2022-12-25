package com.example.aorgia.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicatorWithErrorSnackbar(
    loading: MutableState<Boolean>,
    error: MutableState<Boolean>,
    errorText: String,
    task: () -> Unit = {  }
) {
    Box(Modifier.fillMaxSize()) {
        val loadingPos = LocalConfiguration.current.screenWidthDp.dp / 2 - 26.dp
        if (loading.value) {
            CircularProgressIndicator(
                Modifier
                    .align(Alignment.CenterEnd)
                    .padding(
                        end = loadingPos
                    )
            )
        }
        if (error.value) {
            ErrorSnackbar(
                errorText,
                Modifier
                    .align(Alignment.BottomEnd),
                error
            )
            task()
        }
    }
}