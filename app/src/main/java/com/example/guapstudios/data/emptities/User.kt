package com.example.guapstudios.data.emptities

data class User(
    val login: String,
    val email: String,
    val username: String,
    val typeStudio: String,
    val isAdmin: Boolean,
    val score: Int
)
