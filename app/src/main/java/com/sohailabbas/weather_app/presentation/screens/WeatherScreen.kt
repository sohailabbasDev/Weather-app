package com.sohailabbas.weather_app.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sohailabbas.weather_app.presentation.WeatherViewModel
import com.sohailabbas.weather_app.presentation.components.WeatherItemView

@Composable
fun WeatherScreen(
    paddingValues: PaddingValues,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {

    val weatherList by weatherViewModel.weatherForecastState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = weatherViewModel.city,
            onValueChange = { weatherViewModel.city = it },
            singleLine = true,
            placeholder = { Text("Search City, State, Country..") },
            keyboardActions = KeyboardActions(onDone = { weatherViewModel.fetchWeather() }),
            trailingIcon = {
                IconButton (
                    onClick = {
                        weatherViewModel.fetchWeather()
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )
                    },
                )
            }
        )

        if (weatherList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "No data available",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Please search any city, state or country",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } else {
            LazyColumn {
                items(weatherList) { weathers ->
                    WeatherItemView(weathers)
                }
            }
        }
    }
}