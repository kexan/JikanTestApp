package com.example.myapplication.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AnimeRepositoryModule {
    @Singleton
    @Binds
    fun bindAnimeRepository(impl: AnimeRepositoryImpl): AnimeRepository
}