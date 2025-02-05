package com.example.assignment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment.data.dao.NewsDao
import com.example.assignment.model.Article

@Database(entities = [Article::class], version = 3)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object{
        @Volatile
        var newsInstance: NewsDatabase? = null

        fun getNewsDatabase(context: Context): NewsDatabase{
            return newsInstance ?: synchronized(NewsDatabase::class.java){
                val newInstance = Room.databaseBuilder(
                    context,
                    NewsDatabase::class.java,
                    "news_database").fallbackToDestructiveMigration().build()
                newsInstance = newInstance
                newInstance
            }
        }
    }
}