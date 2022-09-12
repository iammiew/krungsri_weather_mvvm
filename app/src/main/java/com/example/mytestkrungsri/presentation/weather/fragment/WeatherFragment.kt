package com.example.mytestkrungsri.presentation.weather.fragment

import android.os.Bundle
import android.view.KeyEvent.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.databinding.FragmentWeatherBinding
import com.example.mytestkrungsri.extensions.hideSoftKeyboard
import com.example.mytestkrungsri.global.*
import com.example.mytestkrungsri.presentation.common.MasterActivity
import com.example.mytestkrungsri.presentation.weather.viewmodel.WeatherViewModel
import com.example.mytestkrungsri.utils.ImageUtils
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: WeatherViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = this
        initView()
        initViewModel()
        initEvent()
        return binding.root

    }

    private fun initView() {
        binding.weatherConstraint.visibility = View.GONE
        if (sharedViewModel.typeWeatherSearch.value?.city.isNullOrEmpty()) {
            sharedViewModel.loadWeatherByCity(defaultCity, metricType)
        } else {
            sharedViewModel.loadDetailWeatherCurrent()
        }
    }

    private fun initEvent() {
        binding.defaultEditText.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyCode == KEYCODE_ENTER) {
                if (!binding.defaultEditText.text.isNullOrEmpty()) {
                    sharedViewModel.loadWeatherByCity(
                        binding.defaultEditText.text.toString(),
                        sharedViewModel.typeWeatherSearch.value?.weather_type.toString()
                    )
                    (activity as MasterActivity).hideSoftKeyboard()
                } else {
                    binding.inputSearchCity.error = getString(R.string.error_search_city_empty)
                }
            }
            false
        }

        binding.defaultEditText.doOnTextChanged { text, start, count, after ->
            binding.inputSearchCity.error = null
        }
    }

    private fun initViewModel() {
        sharedViewModel.showWeatherCurrent.observe(
            viewLifecycleOwner,
            Observer { weathers ->
                binding.weatherConstraint.visibility = View.VISIBLE
                context?.let {
                    binding.imgTypeWeather.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            ImageUtils.getImageDrawable(it, weathers.weather[0].main)
                        )
                    )
                }
            })

        sharedViewModel.errorSearchWeatherByCity.observe(viewLifecycleOwner, Observer {
            binding.inputSearchCity.error = getString(R.string.error_search_city_not_found)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}