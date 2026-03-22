package com.sohailabbas.weather_app.data.remote.dto

data class DailyWeatherUiModel(
    val date: String,
    val description: String,
    val maxTemp: String,
    val minTemp: String,
    val icon: String,
    val hourly: List<HourlyUiModel>
) {
    data class HourlyUiModel(
        val time: String,
        val temp: String,
        val icon: String
    )
}

