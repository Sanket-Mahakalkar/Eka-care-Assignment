package com.example.assignment.repo

import com.example.assignment.base.BaseRepository
import com.example.assignment.data.database.NewsDatabase
import com.example.assignment.data.prefs.ISharedPreference
import com.example.assignment.dispatcher.IDispatcherProvider
import com.example.assignment.manager.api.INetworkManager
import com.example.assignment.manager.db.IDatabaseManager
import javax.inject.Inject

class NewsRepo @Inject constructor(
    networkManager: INetworkManager,
    preferenceHelper: ISharedPreference,
    appDispatcher: IDispatcherProvider,
    newsDatabase: NewsDatabase,
    databaseManager: IDatabaseManager
): BaseRepository(networkManager, preferenceHelper, appDispatcher, newsDatabase, databaseManager)  {
}