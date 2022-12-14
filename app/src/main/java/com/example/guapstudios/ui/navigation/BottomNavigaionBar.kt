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
import com.example.guapstudios.ui.theme.Gray
import com.example.guapstudios.ui.theme.Magenta

@Composable
fun BottomNavigationBar(navController: NavController) {

    val state by navController.currentBackStackEntryAsState()
    val route = state?.destination?.route

    if (route != AuthorizationScreens.LoginScreen.route
        && route != AuthorizationScreens.RegisterScreen.route
        && route != Screens.Splash.route
    ) {
        BottomNavigation(backgroundColor = Gray) {
            listOfScreensForBottomNav.forEach { screen ->

                val iseSelected = route == screen.route

                BottomNavigationItem(
                    selected = iseSelected,
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
                            tint = if (iseSelected) Magenta else Color.Gray,
                            contentDescription = screen.text,
                            modifier = Modifier.size(
                                if (iseSelected) 30.dp else 25.dp
                            )
                        )
                    },
                    label = {
                        Text(text = screen.text, color = Color.Gray)
                    }
                )
            }
        }
    }
}