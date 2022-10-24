package com.example.guapstudios.data.modelForJSON

@kotlinx.serialization.Serializable
data class ListStringReceiveModel(
    val ides: Array<String>
)

@kotlinx.serialization.Serializable
data class ListResponceModel<T>(
    val tasks: List<T>,
    val failTasksIndex: List<String>
)
