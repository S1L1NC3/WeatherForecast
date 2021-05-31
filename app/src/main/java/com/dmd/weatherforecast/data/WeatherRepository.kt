package com.dmd.weatherforecast.data

import com.dmd.weatherforecast.api.WeatherApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private var api: WeatherApi) {
    //suspend fun testApiCall() = api.testUrl()
}