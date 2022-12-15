package com.example.guapstudios.ui.features.project.currentProject

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.guapstudios.data.modelForJSON.ProjectUpdateReceiveModel
import com.example.guapstudios.ui.navigation.ProjectScreens
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
        user = authorizationViewModel.user!!,
        projectViewModel = projectViewModel,
        navController = navController
    )
}

@Composable
private fun observeProjectViewModel(
    user: User,
    projectViewModel: ProjectViewModel,
    navController: NavController
) {
    val projects = projectViewModel.projects.observeAsState()
    ConfigBottomSheet(
        projects = projects.value,
        projectViewModel = projectViewModel,
        user = user,
        navController = navController
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ConfigBottomSheet(
    user: User,
    navController: NavController,
    projectViewModel: ProjectViewModel,
    projects: List<Project>?
) {

    BottomActionSheetWithContent(
        action = { name, description ->

            val currentProject = projectViewModel.currentProject.value

            if (currentProject?.id == "") {
                projectViewModel.addProjectInStudious(
                    projectReceiveModel = ProjectReceiveModel(
                        studio = user.typeStudio,
                        adminId = user.login,
                        title = name,
                        description = description
                    )
                )
            } else if (currentProject != null) {
                projectViewModel.updateProjectInStudious(
                    ProjectUpdateReceiveModel(
                        id = currentProject.id,
                        adminId = currentProject.id,
                        title = name,
                        tasks = currentProject.tasks,
                        description = description,
                        events = currentProject.events
                    ),
                    studio = currentProject.studio
                )
            }

            projectViewModel.currentProject.value = null
        },
        title = "",
        description = ""

    ) { state, scope ->

        ObserveCurrentProject(state = state, scope = scope, projectViewModel = projectViewModel)

        MainContent(
            user = user,
            navController = navController,
            projectViewModel = projectViewModel,
            projects = projects,
        )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainContent(
    user: User,
    navController: NavController,
    projectViewModel: ProjectViewModel,
    projects: List<Project>?,
) {
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
                projectViewModel.currentProject.value = Project()
            }
        }

        if (projects != null) {

            cardsProjects(
                projects = projects,
                navController = navController,
                viewModel = projectViewModel,
            )
        } else {
            Text("Нет проектов")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ObserveCurrentProject(
    scope: CoroutineScope,
    state: ModalBottomSheetState,
    projectViewModel: ProjectViewModel
) {
    val project = projectViewModel.currentProject.observeAsState()

    project.value?.let {
        scope.launch {
            state.show()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun cardsProjects(
    projects: List<Project>,
    navController: NavController,
    viewModel: ProjectViewModel,
) {

    val isShowDialogAllert = remember { mutableStateOf<Project?>(null) }

    isShowDialogAllert.value?.let {
        AlertDialogDelete(projectViewModel = viewModel, project = it)
    }

    LazyColumn() {
        items(projects) {

            CardScreen(
                name = it.title,
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .height(130.dp)
                    .combinedClickable(
                        onClick = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "project",
                                it
                            )
                            navController.navigate(ProjectScreens.DetailProject.route)
                        },
                        onLongClick = {
                            isShowDialogAllert.value = it
                        }
                    )
            )
        }
    }
}

@Composable
fun AlertDialogDelete(projectViewModel: ProjectViewModel, project: Project) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Действия с проектом") },
            text = { Text(text = "Какие действия вы хотите совершить над проектом?") },
            buttons = {
                Button(onClick = {
                    projectViewModel.deleteProject(
                        projectDeleteReceiveModel = ProjectDeleteReceiveModel(
                            id = project.id,
                            studioId = project.studio
                        )
                    )
                    openDialog.value = false
                }) {
                    Text("Удалить")
                }

                Button(onClick = {
                    projectViewModel.currentProject.value = project
                    openDialog.value = false
                }) {
                    Text("Редактировать")
                }
            }
        )
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

