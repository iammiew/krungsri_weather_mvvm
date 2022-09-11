package com.example.mytestkrungsri.data.entities

import java.io.FileDescriptor

class WeatherEntities {

    class Weather(
        var coord: CoordLatLong,
        var weather: List<WeatherData>,
        var base: String,
        var main: WeatherMain,
        var visibility: Int,
        var wind: WindData,
        var clouds: CloudsData,
        var dt: String,
        var sys: SysData,
        var timezone: Int,
        var id: Int,
        var name: String,
        var cod: Int,
        var type: String,
        var weatherCurrent: String,
        var currentUnits: String,
        var changeUnits: String
    )

    data class CoordLatLong(
        var lon: Double,
        var lat: Double
    )

    data class WeatherData(
        var id: Int,
        var main: String,
        var description: String,
        var icon: String
    )

    data class WeatherMain(
        var temp: Double,
        var feels_like: Double,
        var temp_min: Double,
        var temp_max: Double,
        var pressure: Int,
        var humidity: Int
    )

    data class WindData(
        var speed: Double,
        var deg: Int,
        var gust: Double
    )

    data class CloudsData(
        var all: String
    )

    data class SysData(
        var type: Int,
        var id: Int,
        var country: String,
        var sunrise: Int,
        var sunset: Int
    )

    data class SearchCityWeather(
        var weather_type : String,
        var city: String
    )

}