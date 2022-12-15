package com.example.guapstudios.ui.features.project.detailProject

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.R
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.modelForJSON.DeleteTaskReceiveModel
import com.example.guapstudios.data.modelForJSON.TaskDTO
import com.example.guapstudios.ui.theme.Green
import com.example.guapstudios.ui.theme.Red
import com.example.guapstudios.ui.theme.Yellow
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
                ContentTask(projectId = project.id, taskDTO = it, viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ContentTask(projectId: String, taskDTO: TaskDTO, viewModel: TaskInProjectViewmodel) {
    SwipeContainer(
        dismissState = rememberDismissState(initialValue = DismissValue.Default),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        leftIcon = R.drawable.ic_tech_task,
        rightIcon = R.drawable.ic_delete,
        leftSwipeAction = { viewModel.changeIsCheck(taskDTO) },
        rightSwipeAction = {
            viewModel.deleteTask(
                DeleteTaskReceiveModel(
                    projectId = projectId,
                    taskId = taskDTO.id
                )
            )
        }
    ) {
        CardTask(taskDTO = taskDTO)
    }
}

@Composable
fun CardTask(taskDTO: TaskDTO) {

    Card(
        modifier = Modifier
            .fillMaxSize(),
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
                text = "Тип: ${taskDTO.mark}",
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 16.dp, top = 8.dp)
                    .background(Yellow),
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