package com.app.clearscore.donut.data.network

import com.app.clearscore.donut.data.dto.DonutResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface DonutApiService {
    @GET("endpoint.json")
    suspend fun getDonutScore(): Response<DonutResponseDto>
}
