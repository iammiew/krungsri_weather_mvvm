package com.example.mytestkrungsri.data.entities

class ForecastEntities {
    class ForeCast(
        var cod: String,
        var message: Int,
        var cnt: Int,
        var list: List<ForecastList>,
        var city: CityDetail,

    )

    data class ForecastList(
        var dt: Int,
        var main: ForecastMain,
        var weather: List<WeatherDetail>,
        var clouds: CloudsDetail,
        var wind: WindDetail,
        var visibility: Int,
        var pop: Double,
        var sys: SysDetail,
        var dt_txt: String,
        var city: String,
        var country: String,
        var weatherUnits: String

    )

    data class ForecastMain(
        var temp: Double,
        var feels_like: Double,
        var temp_min: Double,
        var temp_max: Double,
        var pressure: Int,
        var sea_level: Int,
        var grnd_level: Int,
        var humidity: Int,
        var temp_kf: Double
    )

    data class WeatherDetail(
        var id: Int,
        var main: String,
        var description: String,
        var icon: String
    )

    data class CloudsDetail(
        var all: Int
    )

    data class WindDetail(
        var speed: Double,
        var deg: Int,
        var gust: Double
    )

    data class SysDetail(
        var pod: String
    )

    data class CityDetail(
        var id: Int,
        var name: String,
        var coord: CoordDetail,
        var country: String,
        var population: Int,
        var timezone: Int,
        var sunrise: Int,
        var sunset: Int
    )

    data class CoordDetail(
        var lat: Double,
        var lon: Double
    )

    data class ForecastCity(
        var city: String,
        var country: String,
        var weatherUnits: String
    )

}