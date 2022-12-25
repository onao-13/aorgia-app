package com.example.aorgia.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aorgia.components.values.ComponentWidth
import com.example.aorgia.ui.theme.LightDirtyGray
import com.example.aorgia.ui.theme.MediumGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(
    inputText: MutableState<String>,
    modifier: Modifier = Modifier
) {
    TextField(
        value = inputText.value,
        onValueChange = { inputText.value = it },
        modifier = modifier
            .fillMaxWidth(ComponentWidth.MainWidth85f.width)
            .height(60.dp),
        enabled = true,
        singleLine = true,
        shape =  RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = LightDirtyGray,
            containerColor = MediumGray,
            cursorColor = LightDirtyGray,
            focusedIndicatorColor = LightDirtyGray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyShapeTextField(
    text: MutableState<String>,
    modifier: Modifier
) {
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = modifier
            .fillMaxWidth(ComponentWidth.MainWidth85f.width)
            .height(60.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = LightDirtyGray,
            containerColor = Color.Transparent,
            cursorColor = LightDirtyGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent
        )
    )
}

