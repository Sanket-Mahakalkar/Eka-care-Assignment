package com.example.assignment.repo

import com.example.assignment.base.BaseRepository
import com.example.assignment.data.database.NewsDatabase
import com.example.assignment.data.prefs.ISharedPreference
import com.example.assignment.dispatcher.IDispatcherProvider
import com.example.assignment.manager.api.INetworkManager
import com.example.assignment.manager.db.IDatabaseManager
import com.example.assignment.model.Article
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsDetailRepo @Inject constructor(
    networkManager: INetworkManager,
    preferenceHelper: ISharedPreference,
    appDispatcher: IDispatcherProvider,
    newsDatabase: NewsDatabase,
    databaseManager: IDatabaseManager
): BaseRepository(networkManager, preferenceHelper, appDispatcher, newsDatabase, databaseManager)  {

    suspend fun saveArticle(article: Article) = withContext(getDispatcher().io())  {
        getDatabaseManager().saveFavNews(article)
    }
}