package com.example.guapstudios.ui.features.project.currentProject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.ui.theme.Magenta2

@Composable
fun CardScreen(
    name: String,
    fontSize: TextUnit,
    modifier: Modifier,
) {
    Card(
        shape = RoundedCornerShape(16.dp), modifier = modifier,
    ) {
        Box {
            BackgroundForCard(background = Color.White)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    color = Color.Black,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
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