package com.dmd.weatherforecast.api

import com.dmd.weatherforecast.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(ApiConstants.ONE_CALL_ENDPOINT) //TODO: arrange this string values to constants
    suspend fun testUrl(
        @Query(ApiConstants.APP_ID) appid: String,
        @Query(ApiConstants.LAT) lat: String,
        @Query(ApiConstants.LON) lon: String,
        @Query(ApiConstants.EXCLUDE) exclude: String?,
        @Query(ApiConstants.UNITS) units: String?,
        @Query(ApiConstants.LANG) lang: String?
    ): Any

}