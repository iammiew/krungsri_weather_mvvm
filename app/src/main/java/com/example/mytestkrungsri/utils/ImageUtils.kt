package com.example.mytestkrungsri.utils

import android.content.Context
import com.example.mytestkrungsri.global.cloudsWeather
import com.example.mytestkrungsri.global.rainWeather

class ImageUtils {
    companion object {

        fun getImageDrawable(context: Context, weather_type: String): Int {
            if (weather_type.lowercase() == cloudsWeather || weather_type.lowercase() == rainWeather) {
                val pathImage = context.resources.getIdentifier(
                    weather_type.lowercase(),
                    "drawable",
                    context.packageName
                )
                return pathImage
            }

            val pathImageDefault = context.resources.getIdentifier(
                cloudsWeather,
                "drawable",
                context.packageName
            )
            return pathImageDefault
        }

    }
}