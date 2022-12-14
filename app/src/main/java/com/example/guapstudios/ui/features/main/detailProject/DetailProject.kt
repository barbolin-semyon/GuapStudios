package com.example.guapstudios.ui.features.main.detailProject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.ui.navigation.ProjectScreens
import com.example.guapstudios.ui.theme.Blue
import com.example.guapstudios.ui.theme.Gray
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.ui.theme.Magenta2

@Composable
fun DetailProject(navController: NavController, project: Project) {
    Column(
        Modifier
            .background(Brush.linearGradient(listOf(Magenta, Magenta2)))
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

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
                .padding(top = 16.dp, end = 48.dp, bottom = 16.dp)
                .background(Color.White)
        )

        Text(
            text = project.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
            ),
            color = Color.White
        )

        Button(
            onClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "projectId",
                    project.id
                )
                navController.navigate(ProjectScreens.TaskInputProject.route)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Magenta2),
            modifier = Modifier.padding(start = 8.dp),
        ) {
            Text(text = "Создать задачу", color = Color.White)
        }

        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 64.dp),
            backgroundColor = Gray,
        ) {
            TasksInProjectView(project = project, navController = navController)
        }
    }
}