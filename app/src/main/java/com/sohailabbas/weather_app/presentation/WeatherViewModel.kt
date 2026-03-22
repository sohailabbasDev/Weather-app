package com.sohailabbas.weather_app.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohailabbas.weather_app.BuildConfig
import com.sohailabbas.weather_app.data.local.WeatherEntity
import com.sohailabbas.weather_app.data.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherForecastState : MutableStateFlow<List<WeatherEntity>> = MutableStateFlow(emptyList())
    val weatherForecastState =  _weatherForecastState.asStateFlow()

    var city by mutableStateOf("Bengaluru")

    init {
        fetchWeather()
    }

    fun fetchWeather() {
        val apiKey = BuildConfig.WEATHER_API_KEY
        viewModelScope.launch {
            _weatherForecastState.value = weatherRepository.getForecast(city, apiKey)
        }
    }
}