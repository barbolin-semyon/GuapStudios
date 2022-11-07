package com.example.guapstudios.ui.features.splash

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.R
import com.example.guapstudios.data.modelForJSON.TokenModel
import com.example.guapstudios.ui.navigation.Screens
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.ui.theme.Magenta2
import com.example.guapstudios.viewModel.AuthorizationViewModel

@Composable
fun SplashView(navController: NavController, authorizationViewModel: AuthorizationViewModel) {

    Column(
        Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(Magenta2, Magenta))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.s_guap_slogan_vert_w),
            contentDescription = "logo"
        )
    }

    val token = LocalContext.current.getSharedPreferences(
        "authorization", ComponentActivity.MODE_PRIVATE
    ).getString("token", "")

    if (token == "" || token == null) {
        navController.navigate(Screens.AuthorizationScreen.route)
    } else {
        observeStateAuthorization(navController = navController, token, authorizationViewModel)
    }
}

@Composable
private fun observeStateAuthorization(
    navController: NavController,
    token: String,
    authorizationViewModel: AuthorizationViewModel
) {

    authorizationViewModel.getUserByToken(TokenModel(token))
    val state = authorizationViewModel.isAuthorization.observeAsState()

    if (state.value == true) {
        val user = authorizationViewModel.user
        navController.navigate(Screens.MainScreen.route) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    } else if (state.value == false) {
        navController.navigate(Screens.AuthorizationScreen.route)
    }
}