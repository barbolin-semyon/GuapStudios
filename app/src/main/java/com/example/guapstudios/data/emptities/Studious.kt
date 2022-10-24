package com.example.guapstudios.data.emptities

@kotlinx.serialization.Serializable
class Studio(
    val name: String,
    val users: Array<String>,
    val projects: Array<String>,
    val tech_task: Array<String>,
)