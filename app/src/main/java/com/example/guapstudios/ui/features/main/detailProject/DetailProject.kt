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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.ui.theme.Magenta

@Composable
fun DetailProject(navController: NavController, project: Project) {
    Column(
        Modifier
            .background(Magenta)
            .fillMaxSize()
            .padding(16.dp)) {
        Text(text = project.title)

        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 32.dp)
        ) {
            Column {
                Text(text = project.description)
            }
        }
    }
}