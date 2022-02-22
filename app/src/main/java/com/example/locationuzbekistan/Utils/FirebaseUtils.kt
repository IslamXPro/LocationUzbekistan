package com.example.locationuzbekistan.Utils

import android.net.Uri
import com.example.locationuzbekistan.models.ModelClass
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/*
object FirebaseUtils {
        suspend fun uploadPhotos(photosUri: ArrayList<File>): List<ModelClass> {
            val storageRef = Firebase.storage.reference
            val photosUrls = ArrayList<ModelClass>()
            val uploadedPhotosUriLink = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                (photosUri.indices).map { index ->
                    async(Dispatchers.IO) {
                        uploadPhoto(storageRef, photosUri[index])
                    }
                }
            }.awaitAll()

            uploadedPhotosUriLink.forEach { photoUriLink -> photosUrls.add(ModelClass(photoUriLink.toString())) }
            return photosUrls
        }
        private suspend fun uploadPhoto(storageRef: StorageReference, photoFile: File): Uri {
            val fileName = UUID.randomUUID().toString()
            val fileUri = Uri.fromFile(photoFile)

            return storageRef.child(fileName)
                .putFile(fileUri)
                .await()
                .storage
                .downloadUrl
                .await()
        }
    }*/
