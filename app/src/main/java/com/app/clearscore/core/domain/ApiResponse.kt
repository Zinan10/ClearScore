package com.app.clearscore.core.domain

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T, val code: Int) : ApiResponse<T>()
    data class Error(val error: ApiError) : ApiResponse<Nothing>()
}

data class ApiError(val code: Int, val message: String)

/**
 * Transforms the `data` in an `ApiResponse` of type `T` to type `R` using the provided function.
 *
 * - For `ApiResponse.Success`, applies the `transform` function to the `data`
 *   and returns a new `ApiResponse.Success` with transformed data.
 * - For `ApiResponse.Error`, returns the same error without changes.
 *
 * @param T Original data type.
 * @param R Transformed data type.
 * @param transform Function to convert `T` to `R`.
 * @return A new `ApiResponse` with transformed data or the original error.
 */
inline fun <T, R> ApiResponse<T>.map(transform: (T) -> R): ApiResponse<R> {
    return when (this) {
        is ApiResponse.Success -> ApiResponse.Success(transform(this.data), this.code)
        is ApiResponse.Error -> ApiResponse.Error(this.error)
    }
}
