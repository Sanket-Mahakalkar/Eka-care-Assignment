package com.example.assignment.base

import android.content.Context
import com.example.assignment.data.network.ApiService
import com.example.assignment.data.network.Resource
import com.example.assignment.data.network.ServiceGenerator
import com.example.assignment.model.ApiError
import com.example.assignment.utils.AppConstants
import com.example.assignment.utils.NetworkUtils
import com.example.assignment.utils.RetrofitErrorUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

open class BaseNetworkManager @Inject constructor(
    val context: Context
) {

    private val TAG: String = BaseNetworkManager::class.java.simpleName

    @Inject
    lateinit var serviceGenerator: ServiceGenerator

    fun getService(): ApiService {
        return serviceGenerator.getApiService()
    }

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            val apiError = ApiError()
            apiError.message = AppConstants.NO_INTERNET_CONNECTION
            apiError.statusCode = 502
            return Resource.DataError(apiError)
        }
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful){
                    Resource.Success(response.body())
                }else{
                    Resource.DataError(RetrofitErrorUtils.getError(response))
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.DataError(throwable.response()?.let { RetrofitErrorUtils.getError(it) }?:ApiError())
                    }
                    else -> Resource.DataError(ApiError())
                }
            }
        }
    }
}