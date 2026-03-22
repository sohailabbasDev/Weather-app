package com.sohailabbas.weather_app.di

import android.app.Application
import androidx.room.Room
import com.sohailabbas.weather_app.data.remote.api.WeatherApi
import com.sohailabbas.weather_app.data.local.AppDatabase
import com.sohailabbas.weather_app.data.local.WeatherDao
import com.sohailabbas.weather_app.data.repositories.WeatherRepository
import com.sohailabbas.weather_app.domain.WeatherRepositoryImpl
import com.sohailabbas.weather_app.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApi(): WeatherApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

    @Provides
    fun provideDb(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "weather_db").build()

    @Provides
    fun provideDao(db: AppDatabase) : WeatherDao = db.weatherDao()

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        weatherDao: WeatherDao
    ) : WeatherRepository = WeatherRepositoryImpl(weatherApi, weatherDao)
}