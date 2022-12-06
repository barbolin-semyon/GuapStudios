package com.example.guapstudios.ui.features.main.detailProject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.ui.theme.Blue
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.ui.theme.Magenta2

@Composable
fun DetailProject(navController: NavController, project: Project) {
    Column(
        Modifier
            .background(Magenta)
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Text(
            text = project.title,
            fontSize = 48.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = project.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(
                top = 32.dp,
                start = 16.dp,
                end = 16.dp,
            ),
            color = Color.White
        )

        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 64.dp)
        ) {
            Column {
            }
        }
    }
}