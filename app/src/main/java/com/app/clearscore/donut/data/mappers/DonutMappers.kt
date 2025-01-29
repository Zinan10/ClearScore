package com.app.clearscore.donut.data.mappers

import com.app.clearscore.donut.data.dto.DonutResponseDto
import com.app.clearscore.donut.domain.Donut

fun DonutResponseDto.toDonut(): Donut {
    return Donut(
        maxScoreValue = creditReportInfo.score,
        score = creditReportInfo.score
    )
}