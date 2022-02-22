package com.example.locationuzbekistan.models

import android.net.Uri
import com.denzcoskun.imageslider.models.SlideModel
import java.io.Serializable

class ModelClass(private var imagename: String?) : Serializable{


    fun ModalClass(imagename: String?) {
        this.imagename = imagename
    }

    fun getImagename(): String? {
        return imagename
    }

    fun setImagename(imagename: String?) {
        this.imagename = imagename
    }
}