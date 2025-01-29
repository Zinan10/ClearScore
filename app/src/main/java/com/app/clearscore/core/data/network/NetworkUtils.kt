package com.app.clearscore.core.data.network

import com.app.clearscore.core.data.ErrorMessages.GENERIC_ERROR
import com.app.clearscore.core.data.ErrorMessages.NO_DATA_FOUND
import com.app.clearscore.core.domain.ApiError
import com.app.clearscore.core.domain.ApiResponse
import retrofit2.Response

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                ApiResponse.Success(it, response.code())
            } ?: ApiResponse.Error(ApiError(code = response.code(), message = NO_DATA_FOUND))
        } else {
            ApiResponse.Error(ApiError(code = response.code(), message =  response.message()))
        }
    } catch (e: Exception) {
        ApiResponse.Error(ApiError(code = 500, message = GENERIC_ERROR))
    }
}

