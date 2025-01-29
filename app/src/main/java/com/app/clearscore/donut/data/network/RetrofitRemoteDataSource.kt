package com.app.clearscore.donut.data.network

import com.app.clearscore.core.data.network.safeApiCall
import com.app.clearscore.core.domain.ApiResponse
import com.app.clearscore.donut.data.dto.DonutResponseDto
import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(
    private val donutApiService: DonutApiService
) : RemoteDataSource {

    override suspend fun getDonutScore(): ApiResponse<DonutResponseDto> {
       return safeApiCall {
            donutApiService.getDonutScore()
        }
    }

}