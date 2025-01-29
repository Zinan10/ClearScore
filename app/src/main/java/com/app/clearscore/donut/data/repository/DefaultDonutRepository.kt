package com.app.clearscore.donut.data.repository

import com.app.clearscore.donut.data.network.RemoteDataSource
import com.app.clearscore.core.domain.ApiResponse
import com.app.clearscore.core.domain.map
import com.app.clearscore.donut.domain.Donut
import com.app.clearscore.donut.domain.DonutRepository
import javax.inject.Inject

class DefaultDonutRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : DonutRepository {

    override suspend fun getDonutScore(): ApiResponse<Donut> {
        return remoteDataSource.getDonutScore().map { dto ->
            Donut(
                maxScoreValue = dto.creditReportInfo.maxScoreValue,
                score = dto.creditReportInfo.score
            )
        }
    }

}