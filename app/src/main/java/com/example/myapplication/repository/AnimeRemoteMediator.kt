package com.example.myapplication.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.myapplication.api.ApiService
import com.example.myapplication.dao.AnimeDao
import com.example.myapplication.db.AppDb
import com.example.myapplication.entity.AnimeEntity
import com.example.myapplication.entity.toEntity
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    private val service: ApiService,
    private val animeDao: AnimeDao,
    private val db: AppDb
) : RemoteMediator<Int, AnimeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {
        try {
            val response = when (loadType) {
                LoadType.REFRESH -> {
                    service.getRandomAnime()
                }
                LoadType.PREPEND -> {
                    service.getRandomAnime()
                }
                LoadType.APPEND -> {
                    return MediatorResult.Success(true)
                }
            }

            val body = response.body() ?: throw Exception()

            db.withTransaction {
                animeDao.insert(body.toEntity())
            }
            return MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}