package com.example.myapplication.repository

import androidx.paging.PagingData
import com.example.myapplication.dto.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    val data: Flow<PagingData<Anime>>
    suspend fun getRandomAnime()
}