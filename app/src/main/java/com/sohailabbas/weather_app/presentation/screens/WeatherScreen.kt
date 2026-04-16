package com.sohailabbas.weather_app.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sohailabbas.weather_app.presentation.WeatherViewModel
import com.sohailabbas.weather_app.presentation.components.EmptyState
import com.sohailabbas.weather_app.presentation.components.WeatherCard
import com.sohailabbas.weather_app.util.Response
import com.sohailabbas.weather_app.util.TemperatureUnit

@Composable
fun WeatherScreen(
    paddingValues: PaddingValues,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {

    val weatherForecast by weatherViewModel.weatherForecastState.collectAsState()
    val unit = weatherViewModel.temperatureUnit

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            value = weatherViewModel.city,
            onValueChange = { weatherViewModel.city = it },
            singleLine = true,
            placeholder = { Text("Search city, state or country...") },
            keyboardActions = KeyboardActions(
                onDone = { weatherViewModel.fetchWeather() }
            ),
            trailingIcon = {
                IconButton(onClick = {
                    weatherViewModel.fetchWeather()
                }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag("unit_toggle_row")
                .clickable { weatherViewModel.toggleTemperatureUnit() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = if (unit == TemperatureUnit.CELSIUS) "Celsius (°C)" else "Fahrenheit (°F)",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(end = 8.dp)
            )
            Switch(
                modifier = Modifier.testTag("unit_switch"),
                checked = unit == TemperatureUnit.FAHRENHEIT,
                onCheckedChange = { weatherViewModel.toggleTemperatureUnit() }
            )
        }

        when(val result = weatherForecast) {
            is Response.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is Response.Success -> {
                LazyColumn {
                    items(result.data) { day ->
                        WeatherCard(day, unit)
                    }
                }
            }

            is Response.Error -> {
                EmptyState()
            }
        }
    }
}