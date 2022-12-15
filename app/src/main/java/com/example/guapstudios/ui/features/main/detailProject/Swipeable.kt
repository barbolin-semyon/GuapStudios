package com.example.guapstudios.ui.features.main.detailProject

import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.guapstudios.ui.theme.Gray
import com.example.guapstudios.ui.theme.Green
import com.example.guapstudios.ui.theme.Red
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeContainer(
    modifier: Modifier,
    backgroundLeftContent: @Composable () -> Unit,
    backgroundRightContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val dismissState = rememberDismissState(initialValue = DismissValue.Default)

    SwipeToDismiss(modifier = modifier, state = dismissState, background = {
        val direction = dismissState.dismissDirection

        val color = if (direction == DismissDirection.StartToEnd) Green else Red

        Box(Modifier.background(color).fillMaxSize()) {
            if (direction == DismissDirection.StartToEnd) {
                backgroundLeftContent()
            } else {
                backgroundRightContent()
            }
        }
    }) {
        content()
    }
}