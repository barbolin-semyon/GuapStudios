package com.example.guapstudios.ui.features.main.currentProject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.guapstudios.data.modelForJSON.ProjectReceiveModel
import com.example.guapstudios.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CurrentProjectView(
    navController: NavController,
    authorizationViewModel: AuthorizationViewModel,
) {
    val projectViewModel: ProjectViewModel = viewModel()


    projectViewModel.getProjectsInStudious(authorizationViewModel.user!!.typeStudio)
    observeProjectViewModel(
        authorizationViewModel = authorizationViewModel,
        projectViewModel = projectViewModel,
        navController = navController
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun observeProjectViewModel(
    authorizationViewModel: AuthorizationViewModel,
    projectViewModel: ProjectViewModel,
    navController: NavController
) {
    val projects = projectViewModel.projects.observeAsState()

    if (projects.value != null) {
        BottomActionSheetWithContent(
            action = { name, description ->

                val user = authorizationViewModel.user!!

                projectViewModel.addProjectInStudious(
                    projectReceiveModel = ProjectReceiveModel(
                        studio = user.typeStudio,
                        adminId = user.login,
                        title = name,
                        description = description
                    )
                )
            }
        ) { state, scope ->

            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Проекты",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    ButtonToAdd {
                        scope.launch {
                            state.show()
                        }
                    }
                }

                cardsProjects(projects = projects.value!!, navController = navController)
            }
        }

    } else {

    }
}

@Composable
private fun cardsProjects(projects: List<Project>, navController: NavController) {
    LazyColumn() {
        items(projects) {
            CardScreen(
                name = it.title,
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {},
                onSwipe = {},
            )
        }
    }
}

@Composable
private fun ButtonToAdd(action: () -> Unit) {
    IconButton(
        onClick = { action() },
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "add",
                modifier = Modifier.size(32.dp)
            )
        }
    )
}

