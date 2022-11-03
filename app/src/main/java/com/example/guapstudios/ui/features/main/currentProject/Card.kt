package com.example.guapstudios.ui.features.main.currentProject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.guapstudios.ui.theme.Gray
import kotlin.random.Random

@Composable
fun CardScreen(name: String,colorOne: Color, fontSize: TextUnit, modifier: Modifier) {
    Box(modifier = modifier) {
        BackgroundForCard(colorOne = colorOne, colorTwo = Color.White)

        Column(
            Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(text = name, color = Gray, fontSize = fontSize)
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


    })
}