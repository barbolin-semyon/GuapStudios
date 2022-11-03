package com.example.guapstudios.ui.features.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doctors.ui.StudiousSpinner
import com.example.guapstudios.data.modelForJSON.RegisterReciveModel
import com.example.guapstudios.data.emptities.Studio
import com.example.guapstudios.viewModel.AuthorizationViewModel

@Composable
fun RegisterView(navController: NavController) {
    val viewModel: AuthorizationViewModel = viewModel()
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val typeStudio = remember { mutableStateOf<Studio?>(null) }
    val isAdmin = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AuthorizationTitle(text = "Регистрация")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(Modifier.padding(bottom = 16.dp)) {
                FieldsForWrite(
                    login = login,
                    password = password,
                    username = username,
                    email = email,
                    typeStudious = typeStudio,
                    isAdmin = isAdmin
                )
            }
        }

        AuthorizationButton(
            onClick = { viewModel.register(
                RegisterReciveModel(
                login = login.value,
                password = password.value,
                username = username.value,
                email = email.value,
                typeStudio = "event",
                isAdmin = isAdmin.value,
            )
            ) },
            text = "Зарегистрироваться"
        )

        ObserverIsAuthorization(viewModel, navController)
        ObserverSaveToken(viewModel)
    }
}

@Composable
private fun FieldsForWrite(
    login: MutableState<String>,
    password: MutableState<String>,
    username: MutableState<String>,
    email: MutableState<String>,
    typeStudious: MutableState<Studio?>,
    isAdmin: MutableState<Boolean>,
) {
    Column(Modifier.padding(bottom = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

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

        AuthorizationTextField(
            text = username.value,
            onValueChange = { username.value = it },
            textLabel = "Введите фамилию и имя"
        )

        AuthorizationTextField(
            text = email.value,
            onValueChange = { email.value = it },
            textLabel = "Введите email"
        )

        /*StudiousSpinner(
            items = studious,
            tint = Color.Gray,
            hint = "Выберите студию",
            padding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp)
        ) { typeStudious.value = it }

        Row(modifier = Modifier.padding(start = 16.dp)) {
            Checkbox(checked = isAdmin.value, onCheckedChange = {isAdmin.value = it})
            Text ("Вы администратор студии?")
        }*/
    }
}