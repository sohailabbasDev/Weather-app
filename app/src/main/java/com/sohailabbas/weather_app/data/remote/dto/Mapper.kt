package com.sohailabbas.weather_app.data.remote.dto

import com.sohailabbas.weather_app.data.local.WeatherEntity

fun ForecastResponse.toEntities(): List<WeatherEntity> {
    return list.map {
        WeatherEntity(
            city = city.name,
            date = it.dt_txt,
            temperature = it.main.temp,
            description = it.weather.first().description,
            icon = it.weather.first().icon
        )
    }
}

fun ForecastResponse.toDailyList(): List<DailyWeatherUiModel> {

    return list
        .groupBy { it.dt_txt.substring(0, 10) }
        .entries
        .take(3)
        .map { (date, items) ->

            val first = items.first()

            DailyWeatherUiModel(
                date = date,
                description = first.weather.first().description,
                maxTemp = items.maxOf { it.main.temp },
                minTemp = items.minOf { it.main.temp },
                icon = first.weather.first().icon,

                hourly = items.map {
                    DailyWeatherUiModel.HourlyUiModel(
                        time = it.dt_txt.toTime(),
                        temp = it.main.temp,
                        icon = it.weather.first().icon
                    )
                }
            )
        }
}

fun List<WeatherEntity>.toDailyList(): List<DailyWeatherUiModel> {

    return groupBy { it.date.substring(0, 10) }
        .entries
        .take(3)
        .map { (date, items) ->

            val first = items.first()

            DailyWeatherUiModel(
                date = date,
                description = first.description,
                maxTemp = items.maxOf { it.temperature },
                minTemp = items.minOf { it.temperature },
                icon = first.icon,

                hourly = items.map {
                    DailyWeatherUiModel.HourlyUiModel(
                        time = it.date.toTime(),
                        temp = it.temperature,
                        icon = it.icon
                    )
                }
            )
        }
}

