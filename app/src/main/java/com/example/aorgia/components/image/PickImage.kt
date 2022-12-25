package com.example.aorgia.components.image

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PickImage(
    bitmap: MutableState<Bitmap?>,
    imageUri: MutableState<Uri?>,
    button: @Composable (() -> Unit) -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri.value = uri
    }
    Column {
        button {
            launcher.launch(
                "image/*"
            )
        }

        imageUri.value?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver,it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver,it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
        }
    }
}

@Composable
fun PickImageWithPreview(
    bitmap: MutableState<Bitmap?>,
    uri: MutableState<Uri?>,
    shape: Shape,
    size: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)

    ) {
        if (bitmap.value == null) {
            PickImage(bitmap, uri) {
                Button(
                    onClick = it,
                    shape = shape,
                    modifier = Modifier
                        .size(size)
                ) {

                }
            }
        } else {
            bitmap.value?.let { icon ->
                bitmap.value = icon
                PickImage(bitmap, uri) { click ->
                    UserIconImage(
                        size = 250.dp,
                        userIcon = icon,
                        modifier = Modifier.clickable { click() }
                    )
                }
            }
        }
    }
}

@Composable
fun UpdatedPickImageWithPreview(
    link: String,
    icon: MutableState<Uri?>,
    shape: Shape,
    size: Dp,
    modifier: Modifier = Modifier
) {
    val bitmap =  remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        icon.value = uri
    }

    Box(
        modifier
            .fillMaxWidth()
            .wrapContentWidth(CenterHorizontally)
    ) {
        if (icon.value == null) {
            AsyncImage(
                model = link,
                contentDescription = "preview",
                modifier = Modifier
                   .size(size)
                   .clip(shape)
                   .clickable {
                       launcher.launch(
                           "image/*"
                       )
                   },
                contentScale = ContentScale.Crop
            )
        } else {
            PickImageWithPreview(
                bitmap = bitmap,
                uri = icon,
                shape = CircleShape,
                size = size
            )
        }
    }
}