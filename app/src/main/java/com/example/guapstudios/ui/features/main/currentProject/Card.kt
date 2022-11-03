package com.example.guapstudios.ui.features.main.currentProject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.guapstudios.ui.theme.Gray
import kotlin.random.Random

@Composable
fun CardScreen(name: String,colorOne: Color, fontSize: TextUnit, modifier: Modifier) {
    Card(modifier = modifier) {
        Box {
            BackgroundForCard(colorOne = colorOne, colorTwo = Color.White)

            Column(
                Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(text = name, color = Color.Black, fontSize = fontSize, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
private fun BackgroundForCard(colorOne: Color, colorTwo: Color) {
    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {

        drawPath(
            path = Path().apply {
                fillType = PathFillType.EvenOdd
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
            },
            color = colorOne
        )

        drawPath(
            path = Path().apply {
                fillType = PathFillType.EvenOdd
                moveTo(0f, size.height)

                val twoRand = Random.nextInt(4, 6)

                cubicTo(size.width / 1.5f, size.height / 8, size.width / 2, size.height / 1.2f, size.width, 0f)
                lineTo(0f, 0f)
            },
            color = colorTwo
        )
    })
}