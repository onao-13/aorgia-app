package com.example.aorgia.screens.auth.slidescreens

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aorgia.components.LoadingIndicatorWithErrorSnackbar
import com.example.aorgia.components.MainButton
import com.example.aorgia.components.image.PickImageWithPreview
import com.example.aorgia.components.image.UpdatedPickImageWithPreview
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun AddUserIcon(
    username: String,
    userIcon: MutableState<Bitmap?>,
    imageUri: MutableState<Uri?>,
    loading: MutableState<Boolean>,
    isUserExists: MutableState<Boolean>,
    onClick: () -> Unit
) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (icon, nickname, nextButton) = createRefs()

        PickImageWithPreview(
            bitmap = userIcon,
            uri = imageUri,
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(parent.top, margin = 140.dp)
                },
            shape = CircleShape,
            size = 250.dp
        )

        Text(
            text = username,
            color = LightDirtyGray,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(nickname) {
                    top.linkTo(icon.bottom, margin = 20.dp)
                }

        )

        MainButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(nextButton) {
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                },
            title = "Посмотреть профиль",
            enabled = userIcon.value != null
        )

        LoadingIndicatorWithErrorSnackbar(
            loading = loading,
            error = isUserExists,
            errorText = "Пользователь с такой почтой \n существует"
        )
    }
}