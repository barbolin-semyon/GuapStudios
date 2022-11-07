package com.example.guapstudios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.guapstudios.ui.navigation.BottomNavigationBar
import com.example.guapstudios.ui.navigation.GuapNavHost
import com.example.guapstudios.ui.theme.Gray
import com.example.guapstudios.ui.theme.GuapStudiosTheme
import com.example.guapstudios.viewModel.AuthorizationViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: AuthorizationViewModel = viewModel()

            GuapStudiosTheme {
                val navController = rememberNavController()
                val snackBarState = rememberScaffoldState()

                val statusBar = rememberSystemUiController()
                statusBar.setStatusBarColor(Gray)

                Scaffold(
                    scaffoldState = snackBarState,
                    bottomBar = { BottomNavigationBar(navController) },

                    content = {
                        Box(modifier = Modifier.padding(it)) {
                            GuapNavHost(navController = navController, authorizationViewModel = viewModel)
                        }
                    }
                )
            }
        }
    }
}
