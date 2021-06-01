package com.dmd.weatherforecast.di

import com.dmd.weatherforecast.ApiConstants
import com.dmd.weatherforecast.api.WeatherApi
import com.dmd.weatherforecast.data.WeatherRepository
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    @Named("apiKey")
    fun provideApiKey() = ApiConstants.API_KEY

    @Singleton
    @Provides
    fun provideBaseUrl() = ApiConstants.BASE_URL


    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String) : WeatherApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

}