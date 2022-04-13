package com.example.myapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.dto.*

@Entity
data class AnimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String = "",
    val titleJapanese: String? = "",
    val year: Int? = 0,
    val episodes: Int? = 0,
    val synopsis: String = "",
    val score: Float = 0F,
    val imageUrl: String? = "",
    val largeImgUrl: String? = ""
) {
    fun toDto() = Anime(
        Data(
            id,
            title,
            titleJapanese,
            year,
            episodes,
            synopsis,
            score,
            images = Images(Jpg(imageUrl, largeImgUrl))
        )
    )

    companion object {
        fun fromDto(dto: Anime) = AnimeEntity(
            dto.data.id,
            dto.data.title,
            dto.data.titleJapanese,
            dto.data.year,
            dto.data.episodes,
            dto.data.synopsis,
            dto.data.score,
            imageUrl = dto.data.images.jpg.imageUrl,
            largeImgUrl = dto.data.images.jpg.largeImgUrl
        )
    }
}

fun List<Anime>.toEntity(): List<AnimeEntity> = map(AnimeEntity.Companion::fromDto)
fun Anime.toEntity(): AnimeEntity = AnimeEntity.fromDto(this)