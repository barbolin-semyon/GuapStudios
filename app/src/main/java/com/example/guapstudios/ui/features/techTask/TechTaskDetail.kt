package com.example.guapstudios.ui.features.techTask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guapstudios.data.emptities.TechTask
import com.example.guapstudios.viewModel.TechTaskViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TechTaskDetail(techTask: TechTask) {
    val viewModel: TechTaskViewModel = viewModel()

    Column {
        GlideImage(imageModel = "https://picsum.photos/1800", modifier = Modifier.fillMaxWidth())

        Card(modifier = Modifier.fillMaxSize()) {

        }
    }

}