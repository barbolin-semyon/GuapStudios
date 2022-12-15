package com.example.guapstudios.data.emptities

import java.util.*

@kotlinx.serialization.Serializable
data class TechTask(
    val costumer: String,
    val id: String = UUID.randomUUID().toString(),
    val studio: String,
    val title: String,
    val description: String,
    val countPeople: Int,
    val isTake: Boolean,
    val place: String,
    val date: String
)
