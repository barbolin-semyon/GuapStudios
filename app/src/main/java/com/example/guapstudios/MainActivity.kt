package com.example.guapstudios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.guapstudios.ui.navigation.BottomNavigationBar
import com.example.guapstudios.ui.navigation.GuapNavHost
import com.example.guapstudios.ui.theme.GuapStudiosTheme
import com.example.guapstudios.viewModel.AuthorizationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: AuthorizationViewModel = viewModel()

            GuapStudiosTheme {
                val navController = rememberNavController()
                val snackBarState = rememberScaffoldState()

                Scaffold(
                    scaffoldState = snackBarState,
                    bottomBar = { BottomNavigationBar(navController) },

                    content = {
                        GuapNavHost(navController = navController, authorizationViewModel = viewModel)
                    }
                )
            }
        }
    }
}
