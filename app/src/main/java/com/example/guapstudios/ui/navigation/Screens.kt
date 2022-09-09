package com.example.guapstudios.ui.navigation

import androidx.annotation.DrawableRes
import com.example.guapstudios.R

sealed class Screens(val route: String) {
    object AuthorizationScreen : Screens("authorization")
    object MainScreen: Screens("main")
}

sealed class AuthorizationScreen(val route: String) {
    object LoginScreen : AuthorizationScreen("login")
    object RegisterScreen : AuthorizationScreen("register")
}

sealed class MainScreens(val route: String, val text: String, @DrawableRes val icon: Int) {
    object ProfileScreen : MainScreens("profile", "Профиль", R.drawable.ic_profile)

    object TechTaskScreen : MainScreens(
        "techTask", "Техническое задание", R.drawable.ic_tech_task
    )

    object CurrentProjectScreen :
        MainScreens("currentProject", "Текущие проекты", R.drawable.ic_projects)
}

val listOfScreensForBottomNav = listOf(
    MainScreens.CurrentProjectScreen,
    MainScreens.TechTaskScreen,
    MainScreens.ProfileScreen
)

