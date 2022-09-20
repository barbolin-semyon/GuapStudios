package com.example.guapstudios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.guapstudios.ui.navigation.BottomNavigationBar
import com.example.guapstudios.ui.navigation.GuapNavHost
import com.example.guapstudios.ui.navigation.Screens
import com.example.guapstudios.ui.theme.GuapStudiosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuapStudiosTheme {
                val navController = rememberNavController()
                val snackBarState = rememberScaffoldState()

                val t = LocalContext.current.applicationContext.getSharedPreferences("authorization", MODE_PRIVATE).contains("token")

                val token = LocalContext.current.getSharedPreferences("authorization", MODE_PRIVATE)
                    .getString("token", "")

                val startDestination =
                    if (token == "") Screens.AuthorizationScreen.route else Screens.MainScreen.route

                Scaffold(
                    scaffoldState = snackBarState,
                    bottomBar = { BottomNavigationBar(navController) },

                    content = {
                        GuapNavHost(navController = navController, startDestination = startDestination)
                    }
                )
            }
        }
    }
}
