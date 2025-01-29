package com.app.clearscore.donut.data.network

import com.app.clearscore.donut.data.dto.DonutResponseDto
import com.app.clearscore.core.domain.ApiResponse

interface RemoteDataSource {
    suspend fun getDonutScore(): ApiResponse<DonutResponseDto>
}