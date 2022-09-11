package com.example.mytestkrungsri.domain.usecase.weather

import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.data.api.WeatherApiInterface
import com.example.mytestkrungsri.data.entities.ForecastEntities.*
import com.example.mytestkrungsri.data.entities.UseCaseResult
import com.example.mytestkrungsri.data.entities.WeatherEntities.*
import com.example.mytestkrungsri.data.repositories.ForecastRepository
import com.example.mytestkrungsri.data.repositories.WeatherRepository
import com.example.mytestkrungsri.tools.Contextor

interface LoadForeCastByCityUseCase {
    suspend fun execute(
        city: String,
        units: String
    ): UseCaseResult<ForeCast>
}

class LoadForeCastByCityUseCaseImpl(
    private val forecastRepository: ForecastRepository
) : LoadForeCastByCityUseCase {
    override suspend fun execute(
        city: String,
        units: String
    ): UseCaseResult<ForeCast> {
        return try {
            val api_key = Contextor.getInstance().context?.getString(R.string.api_key)

            val result = api_key?.let {
                forecastRepository.getForCastByCity(city, units, 8, api_key)
            }

            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }

}