package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.dto.*
import com.example.myapplication.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@ExperimentalCoroutinesApi
class AnimeViewModel @Inject public constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private val cached = repository
        .data
        .cachedIn(viewModelScope)

    val data: Flow<PagingData<Anime>> = cached

    var currentAnime = Anime(Data())

    suspend fun getRandomAnime() = viewModelScope.launch {
        repository.getRandomAnime()
    }

    fun holdAnime(anime: Anime) {
        currentAnime = anime
    }
}