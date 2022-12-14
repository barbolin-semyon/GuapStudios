package com.example.guapstudios.ui.features.main.detailProject

import android.graphics.Color
import android.renderscript.ScriptGroup.Input
import android.widget.Spinner
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.data.modelForJSON.CreateTaskReceiveModel
import com.example.guapstudios.viewModel.AuthorizationViewModel
import com.example.guapstudios.viewModel.TaskInProjectViewmodel

@Composable
fun InputTask(projectId: String, navController: NavController) {
    val viewModel: TaskInProjectViewmodel = viewModel()

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val mark = remember { mutableStateOf("") }

    val loadedAdd = viewModel.loadedAdd.observeAsState()
    if (loadedAdd.value!!) {
        navController.popBackStack()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Введите данные", fontSize = 40.sp)

        OutlinedTextField(
            label = { Text("Введите заголовок") },
            value = title.value,
            onValueChange = { title.value = it })

        OutlinedTextField(
            label = { Text("Введите Описание") },
            value = description.value,
            onValueChange = { description.value = it })

        OutlinedTextField(
            label = { Text("Введите тип задания") },
            value = mark.value,
            onValueChange = { mark.value = it })

        Button(onClick = {
            viewModel.addTaskInProject(
                CreateTaskReceiveModel(
                    projectId = projectId,
                    title = title.value,
                    description = description.value,
                    user = "",
                    color = "",
                    mark = mark.value
                )
            )
        }) {
            Text("Создать задачу")
        }
    }


}