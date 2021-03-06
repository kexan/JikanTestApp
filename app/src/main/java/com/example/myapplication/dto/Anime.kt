package com.example.myapplication.dto

import com.google.gson.annotations.SerializedName


data class Anime(
    val data: Data,
)

data class Data(
    val id: Long = 0,
    val title: String = "",
    @SerializedName("title_japanese")
    val titleJapanese: String? = "",
    val year: Int? = 0,
    val episodes: Int? = 0,
    val synopsis: String = "",
    val score: Float = 0F,
    val images: Images = Images()
)

data class Images(
    val jpg: Jpg = Jpg()
)

data class Jpg(
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("large_image_url")
    val largeImgUrl: String? = ""
)