package com.example.assignment.manager.api

import com.example.assignment.data.network.Resource
import com.example.assignment.model.Article
import com.example.assignment.model.ArticlesResponse

interface INetworkManager {
    suspend fun getNews(): Resource<ArticlesResponse>
}