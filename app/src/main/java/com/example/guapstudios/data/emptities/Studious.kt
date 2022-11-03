package com.example.guapstudios.data.emptities

import androidx.navigation.Navigator

@kotlinx.serialization.Serializable
class Studio(
    val id: String,
    val users: Array<String>,
    val projects: Array<String>,
    val tech_task: Array<String>,
)