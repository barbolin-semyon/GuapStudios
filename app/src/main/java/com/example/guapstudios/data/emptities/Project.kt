package com.example.guapstudios.data.emptities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.serialization.Serializable
@Parcelize
class Project(
    val adminId: String,
    val studio: String,
    val id: String,
    val title: String,
    val description: String,
    val tasks: Array<String> = arrayOf(),
    val events: Array<String> = arrayOf(),
    val users: Array<String> = arrayOf(),
) : Parcelable