package com.dmd.weatherforecast.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmd.weatherforecast.ApiConstants
import com.dmd.weatherforecast.R
import com.dmd.weatherforecast.models.Daily

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:weatherList")
    fun setThisWeekForListView(recyclerView: RecyclerView, data: List<Daily>?){
        if (recyclerView.adapter == null){
            val weatherAdapter = WeatherAdapter()
            recyclerView.adapter = weatherAdapter
        }

        data?.let {
            (recyclerView.adapter as WeatherAdapter).updateList(it)
        }

    }

    @JvmStatic
    @BindingAdapter("app:src")
    fun setSrc(imageView: ImageView, type: String){
        val drawable: Int = when(type){
            ApiConstants.CLEAR -> R.drawable.sun
            ApiConstants.RAIN -> R.drawable.rain
            ApiConstants.SNOW -> R.drawable.snow
            ApiConstants.CLOUDS -> R.drawable.cloudy
            else -> R.drawable.pika
        }
        imageView.apply {
            Glide.with(imageView.context).load(drawable).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("app:arrangeWindSpeed")
    fun setArrangeWindSpeed(textView: TextView, daily: Daily){
        val valueToArrange = "${textView.resources.getString(R.string.wind_speed)} : ${daily.wind_speed}"
        textView.apply {
            text = valueToArrange
        }

    }
}