package com.example.assignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.model.Article

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
   suspend fun getAllNews(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<Article>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavNews(article: Article)

    @Query("SELECT * FROM news_table WHERE isFav = 1")
    suspend fun getSavedNews(): List<Article>

    @Query("DELETE FROM news_table WHERE title = :title")
    suspend fun deleteNews(title: String?)

}