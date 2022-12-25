package com.example.aorgia.api.firestore

import android.net.Uri
import com.example.aorgia.app.storage.BucketStorage
import com.google.android.gms.tasks.Task
import java.util.*

class UploadImage {
    fun uploadImage(
        firebaseChild: String,
        uri: Uri,
        onCompleteTask: (Task<Uri>) -> Unit,
        onFailureTask: (() -> Unit)? = null
    ) {
        val ref = BucketStorage.firebaseRef.child(firebaseChild + UUID.randomUUID().toString())
        ref.putFile(uri).continueWithTask {
            ref.downloadUrl
        }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onCompleteTask(task)
                }
            }
            .addOnFailureListener {
                onFailureTask?.let { task ->
                    task()
                }
            }
    }
}