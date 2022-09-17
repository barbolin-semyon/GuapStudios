package com.example.guapstudios.data.emptities

data class Studio(
    val id: String,
    val title: String
)

val studious = listOf(
    Studio("event", "Организация мероприятий"),
    Studio("tech", "Техники"),
    Studio("photo", "Фото"),
    Studio("video", "Видео"),
    Studio("dance", "Танцы"),
    Studio("radio", "Радио"),
    Studio("welcome", "Welcome"),
)