package com.dmd.weatherforecast.ui

import android.util.Log
import androidx.lifecycle.*
import com.dmd.weatherforecast.data.WeatherRepository
import com.dmd.weatherforecast.models.Daily
import com.dmd.weatherforecast.models.WeatherResponse
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    private val _response = MutableLiveData<WeatherResponse>()
    val responseWeather: LiveData<WeatherResponse>
        get() = _response

    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        repository.testApiCall().let {response ->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("ServiceResponseTag", "getAllWeatherDatas Error: ${response.code()}")
            }
        }
    }
}