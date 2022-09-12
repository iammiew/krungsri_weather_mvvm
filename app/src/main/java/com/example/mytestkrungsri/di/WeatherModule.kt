package com.example.mytestkrungsri.di

import com.example.mytestkrungsri.data.repositories.ForecastRepository
import com.example.mytestkrungsri.data.repositories.ForecastRepositoryImpl
import com.example.mytestkrungsri.data.repositories.WeatherRepository
import com.example.mytestkrungsri.data.repositories.WeatherRepositoryImpl
import com.example.mytestkrungsri.domain.usecase.weather.LoadForeCastByCityUseCase
import com.example.mytestkrungsri.domain.usecase.weather.LoadForeCastByCityUseCaseImpl
import com.example.mytestkrungsri.domain.usecase.weather.LoadWeatherByCityUseCase
import com.example.mytestkrungsri.domain.usecase.weather.LoadWeatherByCityUseCaseImpl
import com.example.mytestkrungsri.presentation.weather.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val WeatherModule = module {

    factory<WeatherRepository> {
        WeatherRepositoryImpl(
            remoteDataSource = get()
        )
    }

    factory<ForecastRepository> {
        ForecastRepositoryImpl(
            remoteDataSource = get()
        )
    }

    factory<LoadWeatherByCityUseCase> {
        LoadWeatherByCityUseCaseImpl(
            weatherRepository = get()
        )
    }

    factory<LoadForeCastByCityUseCase>{
        LoadForeCastByCityUseCaseImpl(
            forecastRepository = get()
        )
    }

    viewModel {
        WeatherViewModel(
            loadWeatherUseCase = get(),
            loadForeCastByCityUseCase = get()
        )
    }
}