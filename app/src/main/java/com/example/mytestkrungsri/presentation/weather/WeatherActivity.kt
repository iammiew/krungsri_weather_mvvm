package com.example.mytestkrungsri.presentation.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mytestkrungsri.R
import com.example.mytestkrungsri.databinding.ActivityWeatherBinding
import com.example.mytestkrungsri.presentation.common.MasterActivity
import com.example.mytestkrungsri.presentation.weather.fragment.ForecastFragment
import com.example.mytestkrungsri.presentation.weather.fragment.WeatherFragment
import com.example.mytestkrungsri.presentation.weather.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : MasterActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        setContentView(binding.root)
        initDialog(this)
        initControl()
        initEvent()
    }

    private fun initEvent() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { page ->
            when (page.itemId) {
                R.id.weather -> replaceFragment(WeatherFragment())
                R.id.forecast -> replaceFragment(ForecastFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun initControl(){
        replaceFragment(WeatherFragment())
    }
}