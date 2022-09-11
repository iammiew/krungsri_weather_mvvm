package com.example.mytestkrungsri.presentation.weather

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.databinding.FragmentForecastBinding
import com.example.mytestkrungsri.databinding.FragmentWeatherBinding
import com.example.mytestkrungsri.extensions.hideSoftKeyboard
import com.example.mytestkrungsri.global.*
import com.example.mytestkrungsri.presentation.common.MasterActivity
import com.example.mytestkrungsri.presentation.weather.adapter.ForecastAdapter
import com.example.mytestkrungsri.presentation.weather.viewmodel.WeatherViewModel
import com.example.mytestkrungsri.utils.ImageUtils
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.roundToInt

class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: WeatherViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = this
        initView()
        initRecycleView()
        initViewModel()
        return binding.root

    }

    private fun initRecycleView() {
        val recycleViewForecast = binding.recycleWeatherDetail
        recycleViewForecast.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recycleViewForecast.setHasFixedSize(true)
    }

    private fun initView() {
        binding.weatherConstraint.visibility = View.GONE
        sharedViewModel.typeWeatherSearch.value?.let {
            sharedViewModel.loadForecastByCity(it.city, it.weather_type)
        }
    }

    private fun initViewModel() {
        sharedViewModel.showForecastListAdapter.observe(
            viewLifecycleOwner,
            Observer { forecastList ->
                val forecastAdapter = ForecastAdapter(forecastList)
                sharedViewModel.typeWeatherSearch.value?.let { weather ->
                    forecastAdapter.type = weather.weather_type
                }
                binding.recycleWeatherDetail.adapter = forecastAdapter
                binding.recycleWeatherDetail.addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.HORIZONTAL
                    )
                )
            })

        sharedViewModel.showForecastCurrentSelect.observe(viewLifecycleOwner, Observer { forecast ->
            binding.weatherConstraint.visibility = View.VISIBLE
            context?.let {
                binding.imgTypeWeather.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        ImageUtils.getImageDrawable(it, forecast.weather[0].main)
                    )
                )
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}