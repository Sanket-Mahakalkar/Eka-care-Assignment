package com.example.assignment.base

import com.example.assignment.data.prefs.ISharedPreference
import com.example.assignment.dispatcher.IDispatcherProvider
import com.example.assignment.manager.api.INetworkManager

open class BaseRepository (
    private val networkManager: INetworkManager,
    private val preferenceHelper: ISharedPreference,
    private val appDispatcher: IDispatcherProvider
){

    fun getNetworkManager() = networkManager

    fun getPreferenceHelper() = preferenceHelper

    fun getDispatcher() = appDispatcher
}