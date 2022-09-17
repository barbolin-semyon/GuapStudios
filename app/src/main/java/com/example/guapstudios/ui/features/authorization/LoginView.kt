package com.example.guapstudios.ui.features.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guapstudios.R
import com.example.guapstudios.data.emptities.LoginReciveModel
import com.example.guapstudios.data.emptities.TokenModel
import com.example.guapstudios.ui.navigation.AuthorizationScreens
import com.example.guapstudios.ui.navigation.Screens
import com.example.guapstudios.ui.theme.Blue
import com.example.guapstudios.ui.theme.Yellow
import com.example.guapstudios.ui.theme.YellowDark
import com.example.guapstudios.ui.theme.YellowLight
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

        Text(
            text = "Авторизация",
            color = Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp
        )

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

        ButtonsAuthorization(
            onClickLogin = { viewModel.login(LoginReciveModel(login.value, password.value)) },
            onClickRegister = {
                navController.navigate(AuthorizationScreens.RegisterScreen.route)
            }
        )

        ObserverIsAuthorization(viewModel, navController)
    }

}

@Composable
fun ObserverIsAuthorization(viewModel: AuthorizationViewModel, navController: NavController) {
    val isAuthorization = viewModel.isAuthorization.observeAsState()

    if (isAuthorization.value == true) {
        navController.navigate(Screens.MainScreen.route) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }

            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun ButtonsAuthorization(onClickLogin: () -> Unit, onClickRegister: () -> Unit) {
    Button(
        onClick = { onClickLogin() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = ButtonDefaults.elevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "Войти", fontSize = 18.sp)
    }

    Button(
        onClick = { onClickRegister() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        elevation = ButtonDefaults.elevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "Зарегистрироваться", fontSize = 18.sp)
    }
}

@Composable
fun IllustrationCard() {

    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .background(Yellow)
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
fun FieldsForWrite(login: MutableState<String>, password: MutableState<String>) {

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

@Composable
fun AuthorizationTextField(
    text: String,
    onValueChange: (newValue: String) -> Unit,
    textLabel: String
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = { Text(text = textLabel) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp)
    )
}