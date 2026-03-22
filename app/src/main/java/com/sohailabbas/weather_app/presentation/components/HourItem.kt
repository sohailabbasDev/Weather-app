package com.sohailabbas.weather_app.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sohailabbas.weather_app.data.remote.dto.DailyWeatherUiModel

@Composable
fun HourItem(item: DailyWeatherUiModel.HourlyUiModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.size(width = 80.dp, height = 100.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = item.time,
                style = MaterialTheme.typography.bodySmall
            )

            AsyncImage(
                model = "https://openweathermap.org/img/wn/${item.icon}@2x.png",
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            Text(
                text = item.temp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}