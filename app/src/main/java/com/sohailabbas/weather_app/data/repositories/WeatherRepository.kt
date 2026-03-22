package com.sohailabbas.weather_app.data.repositories

import com.sohailabbas.weather_app.data.remote.dto.DailyWeatherUiModel
import com.sohailabbas.weather_app.util.Response

interface WeatherRepository {
    suspend fun getForecast(city: String, apiKey : String?): Response<List<DailyWeatherUiModel>>
}