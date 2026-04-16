package com.sohailabbas.weather_app.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohailabbas.weather_app.BuildConfig
import com.sohailabbas.weather_app.data.remote.dto.DailyWeatherUiModel
import com.sohailabbas.weather_app.data.repositories.WeatherRepository
import com.sohailabbas.weather_app.util.Response
import com.sohailabbas.weather_app.util.TemperatureUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherForecastState =
        MutableStateFlow<Response<List<DailyWeatherUiModel>>>(Response.Loading)
    val weatherForecastState = _weatherForecastState.asStateFlow()

    var city by mutableStateOf("Bengaluru")
    
    var temperatureUnit by mutableStateOf(TemperatureUnit.CELSIUS)
        private set

    init {
        // Loading "Bengaluru" forecast by default
        fetchWeather()
    }

    fun fetchWeather() {
        val apiKey = BuildConfig.WEATHER_API_KEY
        _weatherForecastState.value = Response.Loading
        viewModelScope.launch {
            _weatherForecastState.value = weatherRepository.getForecast(city, apiKey)
        }
    }

    fun toggleTemperatureUnit() {
        temperatureUnit = if (temperatureUnit == TemperatureUnit.CELSIUS) {
            TemperatureUnit.FAHRENHEIT
        } else {
            TemperatureUnit.CELSIUS
        }
    }
}