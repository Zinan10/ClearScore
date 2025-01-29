package com.app.clearscore.donut.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DonutResponseDto(
    @SerialName("creditReportInfo") val creditReportInfo: CreditReportInfoDto
)

@Serializable
data class CreditReportInfoDto(
    @SerialName("maxScoreValue") val maxScoreValue: Int,
    @SerialName("score") val score: Int
)


