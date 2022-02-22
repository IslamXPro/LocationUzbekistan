package com.example.locationuzbekistan.models

import android.graphics.Bitmap

class ImageClass {
    var image: String? = null
    var bitmap: Bitmap? = null


    constructor()
    constructor(image: ArrayList<String>?) {
        this.image = image.toString()
    }

    constructor(bitmap: Bitmap?) {
        this.bitmap = bitmap
    }
}