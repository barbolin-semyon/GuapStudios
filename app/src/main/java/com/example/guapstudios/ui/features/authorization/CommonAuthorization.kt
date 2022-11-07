package com.example.guapstudios.ui.features.authorization

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guapstudios.ui.navigation.Screens
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.ui.theme.Magenta2
import com.example.guapstudios.viewModel.AuthorizationViewModel

@Composable
fun AuthorizationTitle(text: String) {
    Text(
        text = text,
        color = Magenta,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
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

@Composable
fun AuthorizationButton(onClick : () -> Unit, text: String, paddingValues: PaddingValues = PaddingValues()) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        elevation = ButtonDefaults.elevation(8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(text = text, fontSize = 18.sp)
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

@SuppressLint("CommitPrefEdits")
@Composable
fun ObserverSaveToken(viewModel: AuthorizationViewModel) {
    val token = viewModel.token.observeAsState()
    val context = LocalContext.current

    if (token.value!!.isNotEmpty()) {
        val pref = context.applicationContext.getSharedPreferences("authorization", MODE_PRIVATE)
        pref.edit().putString("token", token.value).commit()
    }
}