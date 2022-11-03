package com.example.guapstudios.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {

    val state by navController.currentBackStackEntryAsState()
    val route = state?.destination?.route

    if (route != AuthorizationScreens.LoginScreen.route
        && route != AuthorizationScreens.RegisterScreen.route
        && route != Screens.Splash.route
    ) {
        BottomNavigation {
            listOfScreensForBottomNav.forEach { screen ->
                BottomNavigationItem(
                    selected = route == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {

                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) {
                                    saveState = true
                                }
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = screen.text,
                            modifier = Modifier.size(25.dp)
                        )
                    },
                    label = { Text(text = screen.text) }
                )
            }
        }
    }
}