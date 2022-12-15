package com.example.guapstudios.data.emptities

import java.text.SimpleDateFormat
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
    private val date: String
) {
    fun getDate(): Date? {
        val format = "HH:mm dd.MM.yyyy"
        return SimpleDateFormat(format).parse(date)
    }

    fun getStringDate() = date
}
