package com.example.assignment.data.network

import com.example.assignment.model.ApiError

sealed class Resource<out T> {
    data class Success<out T>(val data: T?) : Resource<T>()
    data class DataError(val apiError: ApiError?) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$apiError]"
        }
    }

}