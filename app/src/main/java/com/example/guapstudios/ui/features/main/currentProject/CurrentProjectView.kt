package com.example.guapstudios.ui.features.main.currentProject

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
import com.example.guapstudios.ui.navigation.ProjectScreens
import com.example.guapstudios.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
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
        MainContent(
            user = user,
            navController = navController,
            projectViewModel = projectViewModel,
            projects = projects,
            state = state,
            scope = scope
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
    state: ModalBottomSheetState,
    scope: CoroutineScope
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
                scope.launch {
                    state.show()
                }
            }
        }

        if (projects != null) {

            cardsProjects(
                showBottom = {
                    scope.launch {
                        state.show()
                    }
                },
                projects = projects,
                navController = navController,
                viewModel = projectViewModel,
            )
        }

        else {
            Text("Нет проектов")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun cardsProjects(
    projects: List<Project>,
    navController: NavController,
    viewModel: ProjectViewModel,
    showBottom: () -> Job,
) {

    val isShowDialogAllert = remember { mutableStateOf(false)}

    LazyColumn() {
        items(projects) {
            if (isShowDialogAllert.value) {
                AlertDialogDelete(projectViewModel = viewModel, project = it)
            }

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

                        }
                    )
            )
        }
    }
}

@Composable
fun AlertDialogDelete(projectViewModel: ProjectViewModel, project: Project) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Удаление проекта") },
        text = { Text(text = "Вы уверены, что хотите удалить проект?") },
        confirmButton = {
            Button(onClick = {
                projectViewModel.deleteProject(
                    projectDeleteReceiveModel = ProjectDeleteReceiveModel(
                        id = project.id,
                        studioId = project.studio
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

