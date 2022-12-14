package com.example.guapstudios.ui.features.main.detailProject

import android.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.modelForJSON.TaskDTO
import com.example.guapstudios.ui.theme.Gray
import com.example.guapstudios.ui.theme.Green
import com.example.guapstudios.ui.theme.Red
import com.example.guapstudios.viewModel.TaskInProjectViewmodel

@Composable
fun TasksInProjectView(project: Project, navController: NavController) {
    val viewModel: TaskInProjectViewmodel = viewModel()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getTasks(project.tasks)
    })

    val state = viewModel.tasks.observeAsState()

    state.value?.let { tasks ->
        LazyColumn {
            items(tasks) {
                CardTask(taskDTO = it)
            }
        }
    }
}

@Composable
fun CardTask(taskDTO: TaskDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 8.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Canvas(modifier = Modifier.padding(16.dp), onDraw = {
                drawCircle(color = if (taskDTO.isCheck) Green else Red, radius = 8.dp.toPx())
            })
        }

        Column {
            Text(
                text = taskDTO.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )
            Text(
                text = "Исполнитель: ${taskDTO.user}",
                modifier = Modifier.padding(start = 8.dp, bottom = 16.dp),
            )
            Spacer(
                modifier = Modifier
                    .width(200.dp)
                    .height(1.dp)
                    .padding(start = 8.dp)
                    .background(androidx.compose.ui.graphics.Color.Gray)
            )
            Text(
                text = taskDTO.description,
                color = androidx.compose.ui.graphics.Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp)
            )
        }
    }
}