package com.example.guapstudios.data.emptities

data class RegisterReciveModel(
    val login: String,
    val password: String,
    val email: String,
    val username: String,
    val typeStudio: String,
    val isAdmin: Boolean,
)
