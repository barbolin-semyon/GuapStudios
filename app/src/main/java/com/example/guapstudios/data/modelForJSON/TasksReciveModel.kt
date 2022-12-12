package com.example.guapstudios.data.modelForJSON

@kotlinx.serialization.Serializable
class TaskDTO(
    val id: String,
    val title: String,
    val description: String,
    val isCheck: Boolean,
    val user: String,
    val color: String,
    val mark: String
)

@kotlinx.serialization.Serializable
data class CreateTaskReceiveModel(
    val projectId: String,
    val task: TaskDTO
)

@kotlinx.serialization.Serializable
data class DeleteTaskReceiveModel(
    val projectId: String,
    val taskId: String,
)