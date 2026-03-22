package com.sohailabbas.weather_app.domain

import com.sohailabbas.weather_app.data.local.WeatherDao
import com.sohailabbas.weather_app.data.local.WeatherEntity
import com.sohailabbas.weather_app.data.remote.api.WeatherApi
import com.sohailabbas.weather_app.data.repositories.WeatherRepository
import com.sohailabbas.weather_app.util.Constants
import jakarta.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getForecast(city: String, apiKey : String?): List<WeatherEntity> {
        return try {
            //Using this free api key as fallback if unused api key
            val response = api.getForecast(city = city, apiKey = apiKey ?: Constants.API_KEY)

            val daily = response.list

            val entities = daily.map {
                WeatherEntity(
                    city = response.city.name,
                    date = it.dt_txt,
                    temperature = it.main.temp,
                    description = it.weather.first().description,
                    icon = it.weather.first().icon
                )
            }

            dao.deleteCity(city)
            dao.insertAll(entities)

            entities
        } catch (e: Exception) {
            // fallback to offline
            e.printStackTrace()
            dao.getWeather(city)
        }
    }
}