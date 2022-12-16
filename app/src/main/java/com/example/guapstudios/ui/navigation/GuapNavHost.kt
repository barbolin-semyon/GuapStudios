package com.example.guapstudios.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.guapstudios.data.emptities.Project
import com.example.guapstudios.data.emptities.TechTask
import com.example.guapstudios.ui.features.Profile
import com.example.guapstudios.ui.features.authorization.LoginView
import com.example.guapstudios.ui.features.authorization.RegisterView
import com.example.guapstudios.ui.features.project.currentProject.CurrentProjectView
import com.example.guapstudios.ui.features.project.detailProject.DetailProject
import com.example.guapstudios.ui.features.project.detailProject.InputTask
import com.example.guapstudios.ui.features.splash.SplashView
import com.example.guapstudios.ui.features.techTask.TechTaskDetail
import com.example.guapstudios.ui.features.techTask.TechTaskListView
import com.example.guapstudios.viewModel.AuthorizationViewModel

@Composable
fun GuapNavHost(navController: NavHostController, authorizationViewModel: AuthorizationViewModel) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        authorization(navController = navController)
        main(navController = navController, authorizationViewModel)

        composable(Screens.Splash.route) {
            SplashView(
                navController = navController,
                authorizationViewModel = authorizationViewModel
            )
        }
    }
}

private fun NavGraphBuilder.authorization(navController: NavController) {
    navigation(
        startDestination = AuthorizationScreens.LoginScreen.route,
        route = Screens.AuthorizationScreen.route
    ) {
        composable(route = AuthorizationScreens.LoginScreen.route) {
            LoginView(navController = navController)
        }

        composable(AuthorizationScreens.RegisterScreen.route) {
            RegisterView(navController = navController)
        }
    }
}

private fun NavGraphBuilder.main(
    navController: NavHostController,
    authorizationViewModel: AuthorizationViewModel
) {
    navigation(
        startDestination = MainScreens.ProjectScreen.route,
        route = Screens.MainScreen.route
    ) {
        composable(MainScreens.ProfileScreen.route) {
            Profile(authorizationViewModel)
        }

        project(navController, authorizationViewModel)
        techTask(navController, authorizationViewModel)
    }
}

private fun NavGraphBuilder.project(
    navController: NavController,
    authorizationViewModel: AuthorizationViewModel
) {
    navigation(
        startDestination = ProjectScreens.ListProject.route,
        route = MainScreens.ProjectScreen.route
    ) {
        composable(ProjectScreens.ListProject.route) {
            CurrentProjectView(navController, authorizationViewModel = authorizationViewModel)
        }

        composable(ProjectScreens.TaskInputProject.route) {
            val currentProject =
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("projectId")
            currentProject?.let { project ->
                InputTask(projectId = project, navController = navController)
            }
        }

        composable(ProjectScreens.DetailProject.route) {
            val currentProject =
                navController.previousBackStackEntry?.savedStateHandle?.get<Project>("project")
            currentProject?.let { project ->
                DetailProject(navController = navController, project = project)
            }
        }
    }
}

private fun NavGraphBuilder.techTask(
    navController: NavController,
    authorizationViewModel: AuthorizationViewModel
) {
    navigation(
        startDestination = TechTaskScreens.ListTechTask.route,
        route = MainScreens.TechTaskScreen.route
    ) {
        composable(TechTaskScreens.ListTechTask.route) {
            TechTaskListView(authorizationViewModel, navController)
        }

        composable(TechTaskScreens.InputTechTask.route) {

        }

        composable(TechTaskScreens.DetailTechTask.route) {
            val techTask =
                navController.previousBackStackEntry?.savedStateHandle?.get<TechTask>("techTask")

            techTask?.let {
                TechTaskDetail(techTask = techTask)
            }
        }
    }
}