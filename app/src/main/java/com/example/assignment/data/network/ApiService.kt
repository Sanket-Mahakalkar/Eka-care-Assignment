package com.example.assignment.data.network

import com.example.assignment.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Response<ArticlesResponse>
}