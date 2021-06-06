package com.dmd.weatherforecast.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmd.weatherforecast.data.WeatherRepository
import com.dmd.weatherforecast.models.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    var lat: String = ""
    var lon: String = ""

    private val _response = MutableLiveData<WeatherResponse>()
    private val _responseResult = MutableLiveData<Boolean>()
    val responseSuccess: LiveData<Boolean>
        get() = _responseResult
    val responseWeather: LiveData<WeatherResponse>
        get() = _response


    fun refreshData(){ //Might be helpful for swipe refresh
        getWeather(lat, lon)
    }

    private fun getWeather(lat: String, lon: String) = viewModelScope.launch {
        repository.testApiCall(lat, lon).let {response ->
            if (response.isSuccessful){
                _responseResult.postValue(response.isSuccessful)
                _response.postValue(response.body())
            }else{
                _responseResult.postValue(response.isSuccessful)
                Log.d("ServiceResponseTag", "getAllWeatherDatas Error: ${response.code()}")
            }
        }
    }
}