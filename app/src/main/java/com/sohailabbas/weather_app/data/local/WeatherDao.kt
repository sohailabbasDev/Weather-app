package com.sohailabbas.weather_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<WeatherEntity>)

    @Query("SELECT * FROM weather WHERE city = :city")
    suspend fun getWeather(city: String): List<WeatherEntity>

    @Query("DELETE FROM weather WHERE city = :city")
    suspend fun deleteCity(city: String)
}