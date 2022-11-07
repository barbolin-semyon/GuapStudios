package com.example.guapstudios.data.modelForJSON

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ListStringReceiveModel(
    val ides: Array<String>
)

@kotlinx.serialization.Serializable
data class ListResponceModel<T>(
    val tasks: List<T>,
    val failTasksIndex: List<String>
)

@Serializable
data class ProjectReceiveModel(
    val studio: String,
    val adminId: String,
    val title: String,
    val description: String,
)

@Serializable
data class ProjectUpdateReceiveModel(
    val id: String,
    val adminId: String? = null,
    val title: String? = null,
    val tasks: Array<String>? = null,
    val description: String? = null,
    val events: Array<String>? = null,
    val users: Array<String>? = null,
)

@Serializable
data class ProjectDeleteReceiveModel(
    val id: String,
    val studioId: String
)

