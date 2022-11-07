package com.example.guapstudios.ui.features.main.currentProject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.viewModel.AuthorizationViewModel
import com.example.guapstudios.viewModel.ProjectViewModel
import com.example.guapstudios.R
import com.example.guapstudios.ui.theme.*

@Composable
fun CurrentProjectView(
    navController: NavController,
    authorizationViewModel: AuthorizationViewModel,
) {
    val projectViewModel: ProjectViewModel = viewModel()


    projectViewModel.getProjectsInStudious(authorizationViewModel.user!!.typeStudio)
    observeProjectViewModel(projectViewModel = projectViewModel, navController = navController)
}

@Composable
private fun observeProjectViewModel(
    projectViewModel: ProjectViewModel,
    navController: NavController
) {
    val projects = projectViewModel.projects.observeAsState()

    if (projects.value != null) {

        Column {
            Text(
                text = "Проекты",
                modifier = Modifier.padding(bottom = 32.dp, start = 16.dp, top = 32.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            cardsProjects(projects = projects.value!!, navController = navController)
        }

        FUBToAdd(navController = navController)
    } else {

    }
}

@Composable
private fun cardsProjects(projects: List<Project>, navController: NavController) {
    LazyColumn() {
        items(projects) {
            CardScreen(
                name = it.title,
                colorOne = colorsForCard.random(),
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun FUBToAdd(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        FloatingActionButton(
            onClick = { },
            backgroundColor = Magenta2,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "add",
                )
            }
        )
    }
}

val colorsForCard = listOf(
    Yellow, Red, Blue, Green
)

