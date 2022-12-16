package com.example.guapstudios.ui.features.techTask

import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.emptities.TechTask
import com.example.guapstudios.ui.features.project.currentProject.ButtonToAdd
import com.example.guapstudios.ui.navigation.TechTaskScreens
import com.example.guapstudios.ui.theme.*
import com.example.guapstudios.viewModel.AuthorizationViewModel
import com.example.guapstudios.viewModel.TechTaskViewModel
import java.util.*

@Composable
fun TechTaskListView(authorizationViewModel: AuthorizationViewModel, navController: NavController) {
    val techViewModel: TechTaskViewModel = viewModel()

    val tasks = techViewModel.tasks.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        techViewModel.getTechTaskInStudious(authorizationViewModel.user!!.typeStudio)
    })


    Column(Modifier.background(Gray)) {
        TechTaskToolbar(navController)

        if (tasks.value != null) {

            LazyColumn() {
                items(tasks.value!!) { techTask ->
                    ContentCard(techTask, navController, techViewModel)
                }
            }

        }
    }
}

@Composable
private fun TechTaskToolbar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Актуальные ТЗ",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        ButtonToAdd {
            //navigation to create tech task
        }
    }
}

@Composable
private fun ContentCard(
    techTask: TechTask,
    navController: NavController,
    techTaskViewModel: TechTaskViewModel
) {
    val razn = techTask.getDate()?.date?.minus(Calendar.getInstance().time.date)
    val color = if (razn!! <= 3) {
        Red
    } else if (razn <= 7) {
        Yellow
    } else Green

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = color
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(end = 32.dp)
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                text = techTask.title,
                color = Magenta,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = techTask.getStringDate(),
                color = Color.Black
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp, top = 8.dp, bottom = 8.dp, start = 16.dp)
                    .background(Gray)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = "Заказчик ${techTask.costumer}"
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                    text = "Место ${techTask.place}"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("techTask", techTask)
                    navController.navigate(TechTaskScreens.DetailTechTask.route)
                }) {
                    Text(text = "Подробнее", color = Color.White)
                }
            }
        }
    }

}