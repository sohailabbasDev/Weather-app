package com.sohailabbas.weather_app.data.remote.dto

data class ForecastResponse(
    val list: List<WeatherItem>,
    val city: City
) {
    data class WeatherItem(
        val dt_txt: String,
        val main: Main,
        val weather: List<Weather>
    )

    data class Main(
        val temp: Double
    )

    data class Weather(
        val description: String,
        val icon: String
    )

    data class City(
        val name: String
    )
}