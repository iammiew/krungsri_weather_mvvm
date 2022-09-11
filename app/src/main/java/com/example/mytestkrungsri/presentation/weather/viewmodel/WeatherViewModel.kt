package com.example.mytestkrungsri.presentation.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.data.entities.ForecastEntities.*
import com.example.mytestkrungsri.data.entities.UseCaseResult
import com.example.mytestkrungsri.data.entities.WeatherEntities.*
import com.example.mytestkrungsri.domain.usecase.weather.LoadForeCastByCityUseCase
import com.example.mytestkrungsri.domain.usecase.weather.LoadWeatherByCityUseCase
import com.example.mytestkrungsri.global.imperialType
import com.example.mytestkrungsri.global.metricType
import com.example.mytestkrungsri.tools.Contextor
import com.example.mytestkrungsri.tools.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class WeatherViewModel(
    var loadWeatherUseCase: LoadWeatherByCityUseCase,
    var loadForeCastByCityUseCase: LoadForeCastByCityUseCase
) : ViewModel() {

    val forecastCountry = MutableLiveData<ForecastCity>()
    val typeWeatherSearch = MutableLiveData<SearchCityWeather>()
    val showWeatherCurrent = MutableLiveData<Weather>()
    val showForecastListAdapter = MutableLiveData<List<ForecastList>>()
    val showForecastCurrentSelect = MutableLiveData<ForecastList>()
    val errorSearchWeatherByCity = SingleLiveEvent<Unit>()

    fun loadWeatherByCity(city: String, units: String) {
        viewModelScope.launch {
            val results = loadWeatherUseCase.execute(city, units)
            when (results) {
                is UseCaseResult.Success -> {
                    typeWeatherSearch.value = SearchCityWeather(units, city)
                    results.data?.let { weather ->
                        Contextor.getInstance().context?.let { context ->
                            forecastCountry.value =
                                ForecastCity(
                                    weather.name,
                                    weather.sys.country,
                                    context.getString(
                                        if (typeWeatherSearch.value?.weather_type == metricType) R.string.weather_type_celsius else R.string.weather_type_fahrenheit,
                                        weather.main.temp.roundToInt().toString()
                                    )
                                )
                        }
                    }

                    Contextor.getInstance().context?.let { context ->
                        results.data?.weatherCurrent =
                            context.getString(
                                if (units == metricType) R.string.weather_type_celsius else R.string.weather_type_fahrenheit,
                                results.data?.main?.temp?.roundToInt().toString()
                            )
                    }
                    Contextor.getInstance().context?.let { context ->
                        results.data?.currentUnits =
                            context.getString(
                                if (units == metricType) R.string.txt_weather_type_celsius else R.string.txt_weather_type_fahrenheit
                            )
                    }
                    Contextor.getInstance().context?.let { context ->
                        results.data?.changeUnits =
                            context.getString(
                                if (units == metricType) R.string.txt_weather_type_fahrenheit else R.string.txt_weather_type_celsius
                            )
                    }
                    showWeatherCurrent.value = results.data
                }
                is UseCaseResult.Error -> {
                    errorSearchWeatherByCity.call()
                }
            }
        }
    }

    fun switchTypeWeather() {
        typeWeatherSearch.value?.let { current ->
            loadWeatherByCity(
                current.city,
                if (current.weather_type == metricType) imperialType else metricType
            )
        }
    }

    fun loadDetailWeatherCurrent() {
        showWeatherCurrent.value = showWeatherCurrent.value
    }

    fun loadForecastByCity(city: String, units: String) {
        viewModelScope.launch {
            val results = loadForeCastByCityUseCase.execute(city, units)
            when (results) {
                is UseCaseResult.Success -> {
                    results.data?.let { forecast ->
                        showForecastCurrentSelect.value = forecast.list[0]
                        showForecastListAdapter.value = forecast.list
                    }
                }
                is UseCaseResult.Error -> {

                }
            }
        }
    }

}