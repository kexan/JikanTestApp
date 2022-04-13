package com.example.myapplication.repository

import androidx.paging.*
import com.example.myapplication.api.ApiService
import com.example.myapplication.dao.AnimeDao
import com.example.myapplication.db.AppDb
import com.example.myapplication.dto.Anime
import com.example.myapplication.entity.AnimeEntity
import com.example.myapplication.entity.toEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.internal.wait
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread


@Singleton
class AnimeRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val animeDao: AnimeDao,
    appDb: AppDb
) : AnimeRepository {

    @OptIn(ExperimentalPagingApi::class)
    override val data: Flow<PagingData<Anime>> = Pager(
        config = PagingConfig(pageSize = 5),
        remoteMediator = AnimeRemoteMediator(api, animeDao, appDb),
        pagingSourceFactory = animeDao::pagingSource
    ).flow.map { pagingData ->
        pagingData.map(AnimeEntity::toDto)
    }

    override suspend fun getRandomAnime() {
        try {
            delay(1000)
            val response = api.getRandomAnime()
            if (!response.isSuccessful) {
                throw Exception()
            }
            val body = response.body() ?: throw Exception()
            animeDao.insert(body.toEntity())
        } catch (e: IOException) {
            throw Exception()
        } catch (e: Exception) {
            throw Exception()
        }
    }
}