package com.app.clearscore.donut.domain

import com.app.clearscore.core.domain.ApiResponse

interface DonutRepository {
    suspend fun getDonutScore(): ApiResponse<Donut>
}