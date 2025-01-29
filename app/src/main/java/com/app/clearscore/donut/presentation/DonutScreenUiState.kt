package com.app.clearscore.donut.presentation

import com.app.clearscore.donut.domain.Donut

sealed interface DonutScreenUiState {
    data object Initial : DonutScreenUiState
    data object Loading : DonutScreenUiState
    data class Success(val obj: Donut) : DonutScreenUiState
    data class Error(val msg: String) : DonutScreenUiState
}
