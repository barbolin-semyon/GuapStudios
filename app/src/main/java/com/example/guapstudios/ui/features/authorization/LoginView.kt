package com.example.guapstudios.ui.features.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.R
import com.example.guapstudios.data.modelForJSON.LoginReciveModel
import com.example.guapstudios.ui.navigation.AuthorizationScreens
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.ui.theme.Magenta2
import com.example.guapstudios.ui.theme.Yellow
import com.example.guapstudios.viewModel.AuthorizationViewModel

@Composable
fun LoginView(navController: NavController) {
    val viewModel: AuthorizationViewModel = viewModel()
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AuthorizationTitle(text = "Авторизация")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(Modifier.padding(bottom = 16.dp)) {
                IllustrationCard()
                FieldsForWrite(login = login, password = password)
            }
        }

        AuthorizationButton(
            onClick = { viewModel.login(LoginReciveModel(login.value, password.value)) },
            text = "Войти"
        )

        AuthorizationButton(
            onClick = { navController.navigate(AuthorizationScreens.RegisterScreen.route) },
            text = "Зарегистрироваться",
            paddingValues = PaddingValues(top = 8.dp)
        )

        ObserverIsAuthorization(viewModel, navController)
        ObserverSaveToken(viewModel)
    }
}

@Composable
fun IllustrationCard() {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .background(brush = Brush.verticalGradient(listOf(Magenta2, Magenta)))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_woman_working),
            contentDescription = "",
            modifier = Modifier.padding(16.dp)
        )
    }

}

@Composable
private fun FieldsForWrite(login: MutableState<String>, password: MutableState<String>) {

    AuthorizationTextField(
        text = login.value,
        onValueChange = { login.value = it },
        textLabel = "Введите логин"
    )

    AuthorizationTextField(
        text = password.value,
        onValueChange = { password.value = it },
        textLabel = "Введите пароль"
    )
}