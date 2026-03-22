package com.sohailabbas.weather_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val city: String,
    val date: String,
    val temperature: Double,
    val description: String,
    val icon: String
)