package com.app.clearscore.donut.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.clearscore.R
import com.app.clearscore.core.presentation.extensions.ShowToast
import com.app.clearscore.donut.domain.Donut

@Composable
fun DonutScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: DonutViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    when (val result = state) {
        is DonutScreenUiState.Error -> ShowToast(message = result.msg)
        DonutScreenUiState.Initial -> Unit
        DonutScreenUiState.Loading -> ShowLoader()

        is DonutScreenUiState.Success -> DonutView(result.obj)
    }
}

@Composable
private fun ShowLoader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun DonutView(donut: Donut) {
    val percentage = donut.score.toFloat() / donut.maxScoreValue.toFloat()
    val donutColor = colorResource(id = R.color.donut_color_orange)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            drawCircle(
                color = Color.LightGray,
                style = Stroke(width = 20f)
            )

            val margin = 25f // Margin that will create space between the outer circle and inner arc
            val innerRadius =
                size.minDimension / 2 - margin // Calculating the inner radius for the arc

            drawArc(
                color = donutColor,
                startAngle = -90f,
                sweepAngle = 360f * percentage,
                useCenter = false,
                // Size of the arc (diameter of the inner circle), based on inner radius
                size = Size(innerRadius * 2, innerRadius * 2),
                style = Stroke(width = 15f, cap = StrokeCap.Round),
                /** Offset for positioning the arc within the canvas,
                 *  adding margin to create space between the arc and outer circle
                 **/
                topLeft = Offset(margin, margin)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.you_credit_score),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "${donut.score}",
                fontSize = 32.sp,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = donutColor
            )
            Text(
                text = stringResource(id = R.string.donut_max_score_label, donut.maxScoreValue),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
