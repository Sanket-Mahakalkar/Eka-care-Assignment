package com.example.assignment.utils

import android.util.Log
import com.example.assignment.model.ApiError
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.Response
import java.io.IOException

class RetrofitErrorUtils {

    companion object {
        private val TAG: String = RetrofitErrorUtils::class.java.simpleName
        fun getError(response: Response<*>): ApiError {
            val gson = GsonBuilder().create()
            var error = ApiError()
            try {
                if (response.errorBody() != null) {
                    error = gson.fromJson(response.errorBody()?.string(), ApiError::class.java)
                }
            } catch (e: IllegalStateException) {
                Log.e(TAG, "getError:  " + e.message);
                error = ApiError()
            } catch (e: JsonSyntaxException) {
                Log.e(TAG, "getError:  " + e.message);
                error = ApiError()
            } catch (e: IOException) {
                Log.e(TAG, "getError:  " + e.message);
                error = ApiError()
            }

            return error
        }
    }
}