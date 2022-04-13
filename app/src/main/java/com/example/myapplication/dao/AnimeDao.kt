package com.example.myapplication.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM AnimeEntity ORDER BY id DESC")
    fun pagingSource(): PagingSource<Int, AnimeEntity>

    @Query("SELECT * FROM AnimeEntity ORDER BY id DESC")
    fun getAll(): Flow<List<AnimeEntity>>

    @Query("SELECT COUNT(*) == 0 FROM AnimeEntity")
    suspend fun isEmpty(): Boolean

    @Query("SELECT COUNT(*) FROM AnimeEntity")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animes: List<AnimeEntity>)
}