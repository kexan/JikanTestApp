package com.example.myapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.dto.*
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

@Entity
data class AnimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String = "",
    val synopsis: String = ""
) {
    fun toDto() = Anime(
        Data(id, title, synopsis)
    )

    companion object {
        fun fromDto(dto: Anime) = AnimeEntity(dto.data.id, dto.data.title, dto.data.synopsis)
    }
}

fun List<Anime>.toEntity(): List<AnimeEntity> = map(AnimeEntity.Companion::fromDto)
fun Anime.toEntity(): AnimeEntity = AnimeEntity.fromDto(this)