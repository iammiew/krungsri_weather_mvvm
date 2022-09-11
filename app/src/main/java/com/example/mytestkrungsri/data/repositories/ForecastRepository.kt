package com.example.mytestkrungsri.data.repositories

import com.example.mytestkrungsri.data.api.WeatherApiInterface
import com.example.mytestkrungsri.data.entities.ForecastEntities.*

interface ForecastRepository {
    suspend fun getForCastByCity(
        city: String,
        units: String,
        cntDays: Int,
        api_key: String
    ): ForeCast
}

class ForecastRepositoryImpl(
    private val remoteDataSource: WeatherApiInterface
) : ForecastRepository {
    override suspend fun getForCastByCity(
        city: String,
        units: String,
        cntDays: Int,
        api_key: String
    ): ForeCast {
        return remoteDataSource.getForecast(city, units, cntDays, api_key)
    }
}