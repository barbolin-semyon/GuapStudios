package com.example.guapstudios.ui.features.techTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guapstudios.data.emptities.TechTask
import com.example.guapstudios.ui.navigation.TechTaskScreens
import com.example.guapstudios.ui.theme.Gray
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.viewModel.AuthorizationViewModel
import com.example.guapstudios.viewModel.TechTaskViewModel
import com.skat.database.tech_task.TechTaskUpdateExecutor
import com.skat.database.tech_task.TechTaskUpdateIsTake
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TechTaskDetail(techTask: TechTask, authorizationViewModel: AuthorizationViewModel) {
    val viewModel: TechTaskViewModel = viewModel()

    Box(
        Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    techTask.getColor(),
                    Color.White
                )
            )
        )
    ) {

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 100.dp),
            text = techTask.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 64.sp
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            shape = RoundedCornerShape(topEnd = 64.dp),
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                TextWithValueAndKey(
                    key = "Время: ",
                    text = techTask.getStringDate().substring(startIndex = 0, endIndex = 5),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )

                TextWithValueAndKey(
                    key = "Дата: ",
                    text = techTask.getStringDate().substring(startIndex = 5)
                )

                TextWithValueAndKey(
                    key = "Заказчик: ",
                    text = techTask.costumer
                )

                TextWithValueAndKey(
                    key = "Место: ",
                    text = techTask.place
                )

                Text(
                    text = techTask.description,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )

                if (techTask.executor == "") {
                    Button(onClick = {
                        viewModel.updateExecutorInTechStudious(
                            TechTaskUpdateExecutor(
                                id = techTask.id,
                                executor = authorizationViewModel.user!!.login
                            )
                        )
                    }) {
                        Text("Взять ТЗ")
                    }
                }

                if (authorizationViewModel.user!!.isAdmin) {

                    TextWithValueAndKey(key = "Исполнитель", text = techTask.executor)

                    Button(onClick = {
                        viewModel.updateIsTakeInTechStudious(
                            TechTaskUpdateIsTake(
                                id = techTask.id,
                                isTake = false
                            )
                        )
                    }
                    ) {
                        Text("Подтвердить")
                    }

                    Button(onClick = {
                        viewModel.updateIsTakeInTechStudious(
                            TechTaskUpdateIsTake(
                                id = techTask.id,
                                isTake = true
                            )
                        )
                    }
                    ) {
                        Text("Отклонить")
                    }
                }
            }

        }
    }

}

@Composable
fun TextWithValueAndKey(
    key: String,
    text: String,
    modifier: Modifier = Modifier.padding(start = 16.dp, top = 8.dp)
) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(key)
            }

            append(text)
        },
        modifier = modifier,
        fontSize = 20.sp
    )

    Spacer(
        modifier = Modifier
            .height(3.dp)
            .fillMaxWidth()
            .background(Gray)
    )
}