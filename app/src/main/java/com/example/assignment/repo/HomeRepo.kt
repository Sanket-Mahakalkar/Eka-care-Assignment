package com.example.assignment.repo

import com.example.assignment.base.BaseRepository
import com.example.assignment.data.database.NewsDatabase
import com.example.assignment.data.network.Resource
import com.example.assignment.data.prefs.ISharedPreference
import com.example.assignment.dispatcher.IDispatcherProvider
import com.example.assignment.manager.api.INetworkManager
import com.example.assignment.manager.db.IDatabaseManager
import com.example.assignment.model.Article
import com.example.assignment.model.ArticlesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepo @Inject constructor(
    networkManager: INetworkManager,
    preferenceHelper: ISharedPreference,
    appDispatcher: IDispatcherProvider,
    newsDatabase: NewsDatabase,
    databaseManager: IDatabaseManager
): BaseRepository(networkManager, preferenceHelper, appDispatcher, newsDatabase, databaseManager) {

    suspend fun getNews(): Flow<Resource<ArticlesResponse>> = flow {
        val result = getNetworkManager().getNews()
        emit(result)
    }

    suspend fun saveNews(articleList: List<Article>) = withContext(getDispatcher().io()) {
        getDatabaseManager().saveNews(articleList)
    }

    suspend fun getNewsFromDb(): List<Article> = withContext(getDispatcher().io()) {
        getDatabaseManager().getNewsFromDb()
    }
}