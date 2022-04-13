package com.example.myapplication.dto

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type


data class Anime(
    val data: Data,
)

data class Data(
    val id: Long = 0,
    val title: String = "",
    val synopsis: String = "",
    val images: Images = Images()
)

data class Images(
    val jpg: Jpg = Jpg()
)

data class Jpg(
    val image_url: String? = "",
)