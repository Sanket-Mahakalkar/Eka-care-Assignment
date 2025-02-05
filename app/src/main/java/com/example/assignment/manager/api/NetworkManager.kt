package com.example.assignment.manager.api

import android.content.Context
import com.example.assignment.base.BaseNetworkManager
import com.example.assignment.data.network.Resource
import com.example.assignment.model.Article
import com.example.assignment.model.ArticlesResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkManager @Inject constructor(
    @ApplicationContext context: Context
): BaseNetworkManager(context), INetworkManager {

    override suspend fun getNews(): Resource<ArticlesResponse> {
        return safeApiCall {
            getService().getNews("tesla","718b9a8e426a43adbeb98617b3ba8a28")
            }
        }

}