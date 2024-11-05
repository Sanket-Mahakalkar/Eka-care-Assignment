package com.example.assignment.di

import com.example.assignment.data.prefs.ISharedPreference
import com.example.assignment.data.prefs.SharedPreferenceImpl
import com.example.assignment.dispatcher.AppDispatcherProvider
import com.example.assignment.dispatcher.IDispatcherProvider
import com.example.assignment.manager.api.INetworkManager
import com.example.assignment.manager.api.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkManager(networkManager: NetworkManager): INetworkManager {
        return networkManager
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(sharedPreference: SharedPreferenceImpl): ISharedPreference {
        return sharedPreference
    }

    @Provides
    @Singleton
    fun provideAppDispatchers(appDispatcherProvider: AppDispatcherProvider): IDispatcherProvider {
        return appDispatcherProvider
    }

}