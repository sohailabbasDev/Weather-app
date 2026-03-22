package com.sohailabbas.weather_app.domain

import com.sohailabbas.weather_app.data.local.WeatherDao
import com.sohailabbas.weather_app.data.remote.api.WeatherApi
import com.sohailabbas.weather_app.data.remote.dto.DailyWeatherUiModel
import com.sohailabbas.weather_app.data.remote.dto.toDailyList
import com.sohailabbas.weather_app.data.remote.dto.toEntities
import com.sohailabbas.weather_app.data.repositories.WeatherRepository
import com.sohailabbas.weather_app.util.Constants
import com.sohailabbas.weather_app.util.Response
import jakarta.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getForecast(
        city: String,
        apiKey: String?
    ): Response<List<DailyWeatherUiModel>> {
        return try {

            val response = api.getForecast(city, apiKey ?: Constants.API_KEY)

            val entities = response.toEntities()
            dao.deleteCity(city)
            dao.insertAll(entities)

            Response.Success(response.toDailyList())

        } catch (e: Exception) {

            val cached = dao.getWeather(city)

            if (cached.isNotEmpty()) {
                Response.Success(cached.toDailyList())
            } else {
                Response.Error(e.message)
            }
        }
    }
}