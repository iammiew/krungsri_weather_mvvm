package com.example.mytestkrungsri.data.api

import com.example.mytestkrungsri.data.entities.ForecastEntities.*
import com.example.mytestkrungsri.data.entities.WeatherEntities.*
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") api_key: String
    ): Weather

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("cnt") cntDays: Int,
        @Query("appid") api_key: String
    ): ForeCast
}