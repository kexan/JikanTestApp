package com.example.myapplication.api

import com.example.myapplication.dto.Anime
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("random/anime")
    suspend fun getRandomAnime(): Response<Anime>
}