package com.example.assignment.data.network

import com.example.assignment.data.prefs.ISharedPreference
import com.example.assignment.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class ServiceGenerator @Inject constructor(
    preferenceHelper: ISharedPreference
) {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}