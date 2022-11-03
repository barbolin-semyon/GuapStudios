package com.example.guapstudios.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.guapstudios.ui.features.authorization.LoginView
import com.example.guapstudios.ui.features.authorization.RegisterView
import com.example.guapstudios.ui.features.main.currentProject.CurrentProjectView
import com.example.guapstudios.ui.features.splash.SplashView
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
        startDestination = MainScreens.CurrentProjectScreen.route,
        route = Screens.MainScreen.route
    ) {
        composable(MainScreens.ProfileScreen.route) {

        }
        composable(MainScreens.CurrentProjectScreen.route) {
            CurrentProjectView(navController, authorizationViewModel = authorizationViewModel)
        }
        composable(MainScreens.TechTaskScreen.route) { }
    }
}