package com.example.guapstudios.ui.features.main.currentProject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.guapstudios.R
import com.example.guapstudios.ui.theme.Gray
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.ui.theme.Magenta2
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun CardScreen(
    name: String,
    fontSize: TextUnit,
    modifier: Modifier,
    onClick: () -> Unit,
    onSwipe: () -> Unit,
) {
    val swipeAction = SwipeAction(
        onSwipe = {onSwipe()},
        icon = painterResource(id = R.drawable.ic_delete),
        background = Gray,
    )

    SwipeableActionsBox(
        modifier = Modifier.background(Gray),
        endActions = listOf(swipeAction)
    ) {
        Card(
            modifier = modifier
                .clickable { onClick() },
            shape = RoundedCornerShape(16.dp)
        ) {
            Box {
                BackgroundForCard(background = Color.White)

                Column(
                    Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = name,
                        color = Color.Black,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun BackgroundForCard(background: Color) {
    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {

        drawPath(
            path = Path().apply {
                fillType = PathFillType.EvenOdd
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
            },
            brush = Brush.verticalGradient(listOf(Magenta2, Magenta))
        )

        drawPath(
            path = Path().apply {
                fillType = PathFillType.EvenOdd
                moveTo(0f, size.height)

                cubicTo(
                    size.width / 5f,
                    size.height / 8,
                    size.width / 2,
                    size.height / 1.2f,
                    size.width,
                    0f
                )
                lineTo(0f, 0f)
            },
            color = background
        )
    })
}