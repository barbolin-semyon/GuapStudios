package com.example.guapstudios.data.emptities

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.example.guapstudios.ui.theme.Green
import com.example.guapstudios.ui.theme.Red
import com.example.guapstudios.ui.theme.Yellow
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
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
) : Parcelable {
    fun getDate(): Date? {
        val format = "HH:mm dd.MM.yyyy"
        return SimpleDateFormat(format).parse(date)
    }

    fun getStringDate() = date

    fun getColor(): Color {
        val razn = getDate()?.date?.minus(Calendar.getInstance().time.date)
        return if (razn!! <= 3) {
            Red
        } else if (razn <= 7) {
            Yellow
        } else Green
    }
}
