package com.example.assignment.manager.db

import com.example.assignment.model.Article

interface IDatabaseManager {
     suspend fun saveNews(articleList: List<Article>)

   suspend fun getNewsFromDb(): List<Article>

   suspend fun saveFavNews(article: Article)

    suspend  fun getSavedNews(): List<Article>

    suspend fun deleteNews(title: String?)
}