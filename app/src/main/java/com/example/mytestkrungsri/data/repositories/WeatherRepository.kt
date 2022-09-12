package com.example.mytestkrungsri.data.repositories

import com.example.mytestkrungsri.data.api.WeatherApiInterface
import com.example.mytestkrungsri.data.entities.WeatherEntities

interface WeatherRepository {
    suspend fun getWeatherByCity(
        city: String,
        units: String,
        api_key: String
    ): WeatherEntities.Weather
}

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherApiInterface
) : WeatherRepository {
    override suspend fun getWeatherByCity(
        city: String,
        units: String,
        api_key: String
    ): WeatherEntities.Weather {
        return remoteDataSource.getCurrentWeather(city, units, api_key)
    }
}