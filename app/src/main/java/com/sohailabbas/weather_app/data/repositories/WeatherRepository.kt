package com.sohailabbas.weather_app.data.repositories

import com.sohailabbas.weather_app.data.local.WeatherEntity

interface WeatherRepository {
    suspend fun getForecast(city: String, apiKey : String?): List<WeatherEntity>
}