package com.example.assignment.manager.db

import com.example.assignment.data.dao.NewsDao
import com.example.assignment.model.Article
import javax.inject.Inject

class DatabaseManager @Inject constructor(
    private val newsDao: NewsDao
): IDatabaseManager {

    override suspend fun saveNews(articleList: List<Article>) {
        newsDao.insertNews(articleList)
    }

    override suspend fun getNewsFromDb(): List<Article> {
       return newsDao.getAllNews()
    }

    override suspend fun saveFavNews(article: Article) {
        newsDao.saveFavNews(article)
    }

    override suspend fun getSavedNews(): List<Article> {
        return newsDao.getSavedNews()
    }

    override suspend fun deleteNews(title: String?) {
        newsDao.deleteNews(title)
    }
}