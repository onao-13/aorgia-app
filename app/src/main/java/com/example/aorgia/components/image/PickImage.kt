package com.example.aorgia.components.image

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun PickImage(
    bitmap: MutableState<Bitmap?>,
    imageUri: MutableState<Uri?>,
    button: @Composable (() -> Unit) -> Unit
) {
//    var imageUri by remember {
//        mutableStateOf<Uri?>(null)
//    }
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