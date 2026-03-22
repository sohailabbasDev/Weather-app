package com.sohailabbas.weather_app.data.remote.api

import com.sohailabbas.weather_app.data.remote.dto.ForecastResponse
import com.sohailabbas.weather_app.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
    ): ForecastResponse
}