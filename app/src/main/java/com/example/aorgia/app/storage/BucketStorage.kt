package com.example.aorgia.app.storage

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

object BucketStorage {
    val firebaseRef = Firebase.storage("gs://aorgia.appspot.com").reference
}