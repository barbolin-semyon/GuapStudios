package com.example.guapstudios.ui.features.main.currentProject

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.ui.theme.Blue
import com.example.guapstudios.viewModel.AuthorizationViewModel
import com.example.guapstudios.viewModel.ProjectViewModel

@Composable
fun CurrentProjectView(navController: NavController, authorizationViewModel: AuthorizationViewModel) {
    val projectViewModel: ProjectViewModel = viewModel()
    projectViewModel.getProjectsInStudious(authorizationViewModel.user!!.typeStudio)

    val projects = projectViewModel.projects.observeAsState()

    if (projects.value != null) {
        LazyColumn() {
            items(projects.value!!) {
                CardScreen(name = it.title, colorOne = Blue, fontSize = 22.sp, modifier = Modifier.fillMaxWidth().height(120.dp).padding(8.dp))
            }
        }
    } else {

    }
}