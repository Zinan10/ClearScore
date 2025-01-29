package com.app.clearscore.donut.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.clearscore.core.domain.ApiResponse
import com.app.clearscore.donut.domain.DonutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DonutViewModel @Inject constructor(
    private val donutRepository: DonutRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DonutScreenUiState>(DonutScreenUiState.Initial)
    val state = _state.asStateFlow()

    init {
        getDonutScore()
    }

    private fun getDonutScore() {
        viewModelScope.launch {
            _state.value = DonutScreenUiState.Loading
            val response = donutRepository.getDonutScore()
            withContext(Dispatchers.IO) {
                when (response) {
                    is ApiResponse.Success -> {
                        _state.value = DonutScreenUiState.Success(response.data)
                    }

                    is ApiResponse.Error -> {
                        _state.value =
                            DonutScreenUiState.Error(response.error.message)
                    }
                }
            }
        }
    }

}