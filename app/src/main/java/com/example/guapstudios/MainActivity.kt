package com.example.guapstudios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.guapstudios.ui.navigation.BottomNavigationBar
import com.example.guapstudios.ui.navigation.GuapNavHost
import com.example.guapstudios.ui.theme.GuapStudiosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuapStudiosTheme {
                val navController = rememberNavController()
                val snackBarState = rememberScaffoldState()

                Scaffold(
                    scaffoldState = snackBarState,
                    bottomBar = { BottomNavigationBar(navController) },

                    content = {
                        GuapNavHost(navController = navController)
                    }
                )
            }
        }
    }
}
