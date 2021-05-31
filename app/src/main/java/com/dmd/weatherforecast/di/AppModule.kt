package com.dmd.weatherforecast.di

import com.dmd.weatherforecast.ApiConstants
import com.dmd.weatherforecast.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideRetrofit(BASE_URL : String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}