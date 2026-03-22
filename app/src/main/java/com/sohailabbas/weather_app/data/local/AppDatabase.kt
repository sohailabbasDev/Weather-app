package com.sohailabbas.weather_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sohailabbas.weather_app.data.local.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}