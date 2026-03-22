package com.sohailabbas.weather_app.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sohailabbas.weather_app.data.local.WeatherEntity

@Composable
fun WeatherItemView(item: WeatherEntity) {

    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {

        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.date)
            Text(text = "${item.temperature}°C")
            Text(text = item.description)
        }
        AsyncImage(
            model = "https://openweathermap.org/img/wn/${item.icon}@2x.png",
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
    }
}