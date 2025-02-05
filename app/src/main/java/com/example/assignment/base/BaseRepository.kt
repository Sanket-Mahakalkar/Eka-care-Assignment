package com.example.assignment.base

import com.example.assignment.data.database.NewsDatabase
import com.example.assignment.data.prefs.ISharedPreference
import com.example.assignment.dispatcher.IDispatcherProvider
import com.example.assignment.manager.api.INetworkManager
import com.example.assignment.manager.db.IDatabaseManager

open class BaseRepository (
    private val networkManager: INetworkManager,
    private val preferenceHelper: ISharedPreference,
    private val appDispatcher: IDispatcherProvider,
    private val newsDatabase: NewsDatabase,
    private val databaseManager: IDatabaseManager,
){

    fun getNetworkManager() = networkManager

    fun getPreferenceHelper() = preferenceHelper

    fun getDispatcher() = appDispatcher

    fun getNewsDatabase() = newsDatabase

    fun getDatabaseManager() = databaseManager
}