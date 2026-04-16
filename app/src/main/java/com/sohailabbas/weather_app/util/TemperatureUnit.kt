package com.sohailabbas.weather_app.util

enum class TemperatureUnit {
    CELSIUS, FAHRENHEIT
}

fun Double.toFahrenheit(): Double = (this * 9 / 5) + 32

fun Double.formatTemperature(unit: TemperatureUnit): String {
    val temp = when (unit) {
        TemperatureUnit.CELSIUS -> this
        TemperatureUnit.FAHRENHEIT -> this.toFahrenheit()
    }
    return "${temp.toInt()}°${if (unit == TemperatureUnit.CELSIUS) "C" else "F"}"
}
