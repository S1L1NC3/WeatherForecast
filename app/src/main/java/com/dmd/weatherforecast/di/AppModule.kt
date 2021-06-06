package com.dmd.weatherforecast.di

import com.dmd.weatherforecast.ApiConstants
import com.dmd.weatherforecast.api.WeatherApi
import com.dmd.weatherforecast.util.PermissionUtil
import com.dmd.weatherforecast.util.TimeUtil
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

    @Singleton
    @Provides
    fun provideTimeUtil() = TimeUtil()

    @Singleton
    @Provides
    fun providePermissionUtil() = PermissionUtil()

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String) : WeatherApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

}