package com.example.mytestkrungsri.presentation.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.data.entities.ForecastEntities.*
import com.example.mytestkrungsri.databinding.ItemWeatherListBinding
import com.example.mytestkrungsri.global.metricType
import com.example.mytestkrungsri.presentation.weather.viewmodel.WeatherViewModel
import com.example.mytestkrungsri.tools.Contextor
import com.example.mytestkrungsri.utils.ImageUtils
import kotlinx.android.synthetic.main.item_weather_list.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.roundToInt

class ForecastAdapter(private val forecastList: List<ForecastList>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private lateinit var binding: ItemWeatherListBinding
    var type = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_weather_list,
            parent,
            false
        )
        return ViewHolder(binding, type)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }


    class ViewHolder(
        private val binding: ItemWeatherListBinding,
        private val weather_type: String
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: ForecastList) {
            binding.viewModel = forecast
            binding.txtWeatherValues.text = Contextor.getInstance().context?.getString(
                if (weather_type == metricType) R.string.weather_type_celsius else R.string.weather_type_fahrenheit,
                forecast.main.temp.roundToInt().toString()
            )
            Contextor.getInstance().context?.let {
                binding.imgWeatherList.setImageDrawable(
                    ContextCompat.getDrawable(
                        it,
                        ImageUtils.getImageDrawable(it, forecast.weather[0].main)
                    )
                )
            }
        }
    }
}