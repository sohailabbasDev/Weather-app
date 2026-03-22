package com.sohailabbas.weather_app.util

object HelperFunctions {

    fun Double.toTemp() = "${toInt()}°"

    fun String.toTime(): String = substring(11, 16)
}