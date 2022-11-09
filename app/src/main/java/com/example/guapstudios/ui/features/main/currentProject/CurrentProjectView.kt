package com.example.guapstudios.ui.features.main.currentProject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.guapstudios.data.emptities.User
import com.example.guapstudios.data.modelForJSON.ProjectDeleteReceiveModel
import com.example.guapstudios.data.modelForJSON.ProjectReceiveModel
import com.example.guapstudios.ui.theme.*
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

        val user = authorizationViewModel.user!!

        BottomActionSheetWithContent(
            action = { name, description ->

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

                cardsProjects(
                    projects = projects.value!!,
                    navController = navController,
                    viewModel = projectViewModel,
                    user = user
                )
            }
        }

    } else {

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun cardsProjects(
    projects: List<Project>,
    navController: NavController,
    viewModel: ProjectViewModel,
    user: User
) {

    LazyColumn() {
        items(projects) {
            CardScreen(
                name = it.title,
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                onClick = {

                },

            )

        }
    }
}

@Composable
fun AlertDialogDelete(projectViewModel: ProjectViewModel, user: User, idProject: String = "") {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Удаление проекта") },
        text = { Text(text = "Вы уверены, что хотите удалить проект?") },
        confirmButton = {
            Button(onClick = {
                projectViewModel.deleteProject(
                    projectDeleteReceiveModel = ProjectDeleteReceiveModel(
                        id = idProject,
                        studioId = user.typeStudio
                    )
                )
            }) {
                Text("да")
            }
        }
    )

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

