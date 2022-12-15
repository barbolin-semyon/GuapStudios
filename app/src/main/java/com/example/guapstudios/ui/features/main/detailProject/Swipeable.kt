package com.example.guapstudios.ui.features.main.detailProject

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.guapstudios.ui.theme.Green
import com.example.guapstudios.ui.theme.Red
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeContainer(
    modifier: Modifier,
    dismissState: DismissState,
    leftIcon: Int,
    rightIcon: Int,
    rightSwipeAction: () -> Unit,
    leftSwipeAction: () -> Unit,
    content: @Composable () -> Unit,
) {

    val r = remember { mutableStateOf(false) }

    rememberCoroutineScope().launch {
        if (r.value) {
            dismissState.reset()
            r.value = false
        }
    }

    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
        r.value = true
        rightSwipeAction()
    }

    if (dismissState.isDismissed(DismissDirection.StartToEnd) && r.value.not()) {
        r.value = true
        leftSwipeAction()
    }

    SwipeToDismiss(modifier = modifier, state = dismissState, background = {
        val direction = dismissState.dismissDirection

        val color = if (direction == DismissDirection.StartToEnd) Green else Red

        val arrangement =
            if (direction == DismissDirection.StartToEnd) Arrangement.Start else Arrangement.End

        val icon = if (direction == DismissDirection.StartToEnd) leftIcon else rightIcon

        Row(
            Modifier
                .background(color)
                .fillMaxSize(), horizontalArrangement = arrangement
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = "action")
        }
    }) {
        content()
    }
}