package com.example.guapstudios.ui.features

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guapstudios.ui.theme.Gray
import com.example.guapstudios.ui.theme.Magenta
import com.example.guapstudios.ui.theme.Magenta2
import com.example.guapstudios.viewModel.AuthorizationViewModel
import com.skydoves.landscapist.glide.GlideImage
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Profile(userViewModel: AuthorizationViewModel) {

    userViewModel.user!!.apply {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        listOf(Magenta2, Magenta),
                        tileMode = TileMode.Repeated
                    )
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 200.dp), backgroundColor = Gray,
                elevation = 32.dp,
                shape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        text = username,
                        modifier = Modifier
                            .padding(top = 150.dp)
                            .fillMaxWidth(),
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(top = 64.dp)
                            .height(2.dp)
                            .width(100.dp)
                            .background(Color.Gray)
                    )

                    Text(
                        text = "Студия: $typeStudio",
                        modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                        fontSize = 28.sp,
                    )

                    Text(
                        text = "Очков: $score",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 28.sp,
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 64.dp)
                            .height(2.dp)
                            .width(100.dp)
                            .background(Color.Gray)
                    )

                    Button(onClick = { /*TODO*/ }) {
                        Text(
                            text = "Посмотреть историю \n активности",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Card(
                shape = RoundedCornerShape(200.dp),
                modifier = Modifier
                    .padding(top = 80.dp)
                    .size(250.dp),
                backgroundColor = Gray,
                elevation = 32.dp
            ) {
                GlideImage(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(200.dp)),

                    imageModel = "https://picsum.photos/1800",
                )
            }
        }
    }
}