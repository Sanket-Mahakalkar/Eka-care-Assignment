package com.example.assignment.di

import android.content.Context
import com.example.assignment.data.dao.NewsDao
import com.example.assignment.data.database.NewsDatabase
import com.example.assignment.data.prefs.ISharedPreference
import com.example.assignment.data.prefs.SharedPreferenceImpl
import com.example.assignment.dispatcher.AppDispatcherProvider
import com.example.assignment.dispatcher.IDispatcherProvider
import com.example.assignment.manager.api.INetworkManager
import com.example.assignment.manager.api.NetworkManager
import com.example.assignment.manager.db.DatabaseManager
import com.example.assignment.manager.db.IDatabaseManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
   abstract fun bindNetworkManager(networkManager: NetworkManager): INetworkManager

    @Binds
    @Singleton
    abstract fun bindPreferenceHelper(sharedPreference: SharedPreferenceImpl): ISharedPreference

    @Binds
    @Singleton
    abstract fun bindAppDispatchers(appDispatcherProvider: AppDispatcherProvider): IDispatcherProvider

    @Binds
    @Singleton
    abstract fun bindDatabaseManager(databaseManager: DatabaseManager): IDatabaseManager

    companion object{
        @Provides
        @Singleton
        fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase{
            return NewsDatabase.getNewsDatabase(context)
        }


        @Provides
        @Singleton
        fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao{
            return newsDatabase.newsDao()
        }
    }





}