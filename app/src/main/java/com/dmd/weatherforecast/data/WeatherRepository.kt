package com.dmd.weatherforecast.data

import com.dmd.weatherforecast.api.WeatherApi
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private var api: WeatherApi) {
    @Inject
    @Named("apiKey")
    lateinit var apiKey: String //DI used to get apiKey

    suspend fun testApiCall(lat: String, lon: String) = api.testUrl(apiKey,lat,lon,"","","")
}