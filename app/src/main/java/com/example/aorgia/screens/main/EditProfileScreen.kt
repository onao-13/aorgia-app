package com.example.aorgia.screens.main

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.aorgia.api.model.ProfileApiViewModel
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.*
import com.example.aorgia.components.image.UpdatedPickImageWithPreview
import com.example.aorgia.data.local.LocalUser
import com.example.aorgia.ui.theme.LightDirtyGray

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    updateProfile: (
        username: MutableState<String>,
        tag: MutableState<String>,
        uri: MutableState<Uri?>
    ) -> Unit,
    loading: MutableState<Boolean>,
    error: MutableState<Boolean>
) {
    val updatedUsername = remember { mutableStateOf(LocalUser.Data.username.value) }
    val updatedTag = remember { mutableStateOf(LocalUser.Data.tag.value) }
    val uri = remember { mutableStateOf<Uri?>(null) }

    ConstraintLayout(
        Modifier.background(Color.Black)
    ) {
        val (title, icon, username, tag, saveUpdate, deleteChange) = createRefs()

        MainTitle(
            title = "Изменение профиля",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    top.linkTo(parent.top, margin = 60.dp)
                }
        )

        UpdatedPickImageWithPreview(
            link = LocalUser.Data.icon.value,
            icon = uri,
            shape = CircleShape,
            size = 250.dp,
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(title.bottom, margin = 50.dp)
                }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(username) {
                    top.linkTo(icon.bottom, margin = 30.dp)
                },
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            LittleText("Никнейм")
            EmptyShapeTextField(
                updatedUsername
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(tag) {
                    top.linkTo(username.bottom, margin = 20.dp)
                }
        ) {
            LittleText("Тег")
            EmptyShapeTextField(
                updatedTag
            )
        }


        MainButton(
            onClick = {
                updateProfile(updatedUsername, updatedTag, uri)
            },
            title = "Сохранить изменения",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(saveUpdate) {
                    bottom.linkTo(deleteChange.top, margin = 20.dp)
                }
        )

        MainButton(
            onClick = {
                navController.navigate(Screen.Main.route)
            },
            title = "Отменить",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .constrainAs(deleteChange) {
                    bottom.linkTo(parent.bottom, margin = 40.dp)
                }
        )

        LoadingIndicatorWithErrorSnackbar(
            loading,
            error,
            errorText = "Этот тег занят"
        )
    }
}