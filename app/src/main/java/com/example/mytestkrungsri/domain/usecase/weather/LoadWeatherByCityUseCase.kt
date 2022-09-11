package com.example.mytestkrungsri.domain.usecase.weather

import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.data.api.WeatherApiInterface
import com.example.mytestkrungsri.data.entities.UseCaseResult
import com.example.mytestkrungsri.data.entities.WeatherEntities.*
import com.example.mytestkrungsri.data.repositories.WeatherRepository
import com.example.mytestkrungsri.tools.Contextor

interface LoadWeatherByCityUseCase {
    suspend fun execute(city: String, units: String): UseCaseResult<Weather>
}

class LoadWeatherByCityUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : LoadWeatherByCityUseCase {
    override suspend fun execute(city: String, units: String): UseCaseResult<Weather> {
        return try {
            val api_key = Contextor.getInstance().context?.getString(R.string.api_key)

            val result = api_key?.let {
                weatherRepository.getWeatherByCity(city,units, api_key)
            }

            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }

}