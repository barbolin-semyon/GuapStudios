package com.example.guapstudios.ui.features.main.currentProject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guapstudios.ui.theme.Magenta
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomActionSheetWithContent(
    action: (name: String, description: String) -> Unit,
    content: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit
) {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.White,
        sheetElevation = 12.dp,
        sheetShape = RoundedCornerShape(topEnd = 32.dp),
        sheetState = state,
        sheetContent = { SheetContentAddProject(action = action) }

    ) {
        content(state, scope)
    }
}

@Composable
private fun SheetContentAddProject(action: (name: String, description: String) -> Unit) {

    Text(
        text = "Создание проекта",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp),
        color = Magenta
    )

    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }


    OutlinedTextField(
        value = name.value,
        onValueChange = { name.value = it },
        label = { Text("Название проекта") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )

    OutlinedTextField(
        value = description.value,
        onValueChange = { description.value = it },
        label = { Text("Описание проекта") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { action(name.value, description.value) }
    ) {
        Text(text = "Создать проект")
    }
}

