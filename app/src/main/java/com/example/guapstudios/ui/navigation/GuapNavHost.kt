package com.example.guapstudios.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun GuapNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.AuthorizationScreen.route) {
        composable(Screens.AuthorizationScreen.route) {
            authorization(navController = navController)
            main(navController = navController)
        }
    }
}

@Composable
fun NavGraphBuilder.authorization(navController: NavHostController) {
    navigation(
        startDestination = AuthorizationScreen.LoginScreen.route,
        route = Screens.AuthorizationScreen.route
    ) {
        composable(AuthorizationScreen.LoginScreen.route) {

        }

        composable(AuthorizationScreen.RegisterScreen.route) {

        }
    }
}

@Composable
fun NavGraphBuilder.main(navController: NavHostController) {
    navigation(
        startDestination = MainScreens.CurrentProjectScreen.route,
        route = Screens.MainScreen.route
    ) {
        composable(MainScreens.ProfileScreen.route) {}
        composable(MainScreens.CurrentProjectScreen.route) {}
        composable(MainScreens.TechTaskScreen.route) { }
    }
}