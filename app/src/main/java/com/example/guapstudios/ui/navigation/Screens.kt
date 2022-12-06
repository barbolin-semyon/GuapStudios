package com.example.guapstudios.ui.navigation

import androidx.annotation.DrawableRes
import com.example.guapstudios.R

sealed class Screens(val route: String) {
    object AuthorizationScreen : Screens("authorization")
    object MainScreen: Screens("main")
    object Splash: Screens("splash")
}

sealed class AuthorizationScreens(val route: String) {
    object LoginScreen : AuthorizationScreens("login")
    object RegisterScreen : AuthorizationScreens("register")
}

sealed class MainScreens(val route: String, val text: String, @DrawableRes val icon: Int) {
    object ProfileScreen : MainScreens("profile", "Профиль", R.drawable.ic_profile)

    object TechTaskScreen : MainScreens(
        "techTask", "ТЗ", R.drawable.ic_tech_task
    )

    object ProjectScreen :
        MainScreens("project", "Проекты", R.drawable.ic_projects)
}

sealed class ProjectScreens(val route: String) {
    object ListProject : ProjectScreens("project")
    object DetailProject : ProjectScreens("detailProject")
    object TaskProject : ProjectScreens("taskProject")
}

val listOfScreensForBottomNav = listOf(
    MainScreens.ProjectScreen,
    MainScreens.TechTaskScreen,
    MainScreens.ProfileScreen
)

