package com.example.locationuzbekistan.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.denzcoskun.imageslider.models.SlideModel
import java.io.Serializable
@Entity
class User: Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: String? = null

    var placeName: String? = null
    var description: String? = null
    var locationLink: String? = null
    var imagelist: ArrayList<String>? = null
    var image: String? = null

    constructor(placeName: String?, description: String?, imagelist: ArrayList<String>?) {
        this.placeName = placeName
        this.description = description
        this.imagelist = imagelist
    }


    constructor()
    constructor(imagelist: ArrayList<String>) {
        this.imagelist = imagelist
    }

    constructor(
        id: String?,
        placeName: String?,
        description: String?,
        locationLink: String?,
        imagelist: ArrayList<String>?
    ) {
        this.id = id
        this.placeName = placeName
        this.description = description
        this.locationLink = locationLink
        this.imagelist = imagelist
    }
}