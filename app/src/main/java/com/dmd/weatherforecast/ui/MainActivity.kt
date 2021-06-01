package com.dmd.weatherforecast.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dmd.weatherforecast.R
import com.dmd.weatherforecast.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    @Named("apiKey")
    lateinit var apiKey: String

    private val viewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.responseWeather.observe(this, { listTvShows ->
            Log.d("ServiceResponseTag", "onCreate: $listTvShows" )

        })
        /*var viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(WeatherViewModel::class.java)*/


        setContentView(binding.root)
    }
}