package com.sohailabbas.weather_app.data.remote.dto

data class DailyWeatherUiModel(
    val date: String,
    val description: String,
    val maxTemp: Double,
    val minTemp: Double,
    val icon: String,
    val hourly: List<HourlyUiModel>
) {
    data class HourlyUiModel(
        val time: String,
        val temp: Double,
        val icon: String
    )
}

