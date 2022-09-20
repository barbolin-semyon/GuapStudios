package com.example.guapstudios.ui.navigation

import android.util.Log
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

@Composable
fun GuapNavHost(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        authorization(navController = navController)
        main(navController = navController)
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

private fun NavGraphBuilder.main(navController: NavHostController) {
    navigation(
        startDestination = MainScreens.CurrentProjectScreen.route,
        route = Screens.MainScreen.route
    ) {
        composable(MainScreens.ProfileScreen.route) {

        }
        composable(MainScreens.CurrentProjectScreen.route) {
            CurrentProjectView(navController)
        }
        composable(MainScreens.TechTaskScreen.route) { }
    }
}