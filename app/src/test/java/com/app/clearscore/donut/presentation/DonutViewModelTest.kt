package com.app.clearscore.donut.presentation

import com.app.clearscore.CoroutineTestRule
import com.app.clearscore.core.domain.ApiError
import com.app.clearscore.core.domain.ApiResponse
import com.app.clearscore.donut.domain.Donut
import com.app.clearscore.donut.domain.DonutRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DonutViewModelTest {
    // Allows us to test coroutines in a structured and isolated way
    @get:Rule
    val coroutineTestRule = CoroutineTestRule(StandardTestDispatcher())
    private val mockDonutRepository: DonutRepository = mock(DonutRepository::class.java)
    private lateinit var viewModel: DonutViewModel


    @Test
    fun `getDonutScore emits Success state when API returns data`() = runTest {
        val donutData = Donut(score = 327, maxScoreValue = 700)
        whenever(mockDonutRepository.getDonutScore())
            .thenReturn(ApiResponse.Success(donutData, 200))

        viewModel = DonutViewModel(mockDonutRepository)
        val states = viewModel.state.take(3).toList()
        assertThat(states).containsExactly(
            DonutScreenUiState.Initial,
            DonutScreenUiState.Loading,
            DonutScreenUiState.Success(donutData)
        )
    }

    @Test
    fun `getDonutScore emits Error state when API returns error`() = runTest {
        val errorMessage = "Something went wrong"
        whenever(mockDonutRepository.getDonutScore())
            .thenReturn(ApiResponse.Error(ApiError(message = errorMessage, code = 500)))

        viewModel = DonutViewModel(mockDonutRepository)

        // Advance until all coroutines have completed
        advanceUntilIdle()
        assertThat(viewModel.state.first()).isEqualTo(DonutScreenUiState.Error(errorMessage))
    }

}