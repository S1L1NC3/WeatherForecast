package com.dmd.weatherforecast.ui

import androidx.lifecycle.ViewModel
import com.dmd.weatherforecast.data.WeatherRepository
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

}