package com.dmd.weatherforecast.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmd.weatherforecast.databinding.RowDayBinding
import com.dmd.weatherforecast.models.Daily
import com.dmd.weatherforecast.util.TimeUtil

class WeatherAdapter :RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){
    private var adapterDataList: ArrayList<Daily> = arrayListOf()

    class WeatherViewHolder(val binding: RowDayBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowDayBinding.inflate(layoutInflater)

        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentDaily = adapterDataList[position]
        holder.binding.subConst.setOnClickListener{
            currentDaily.expand = !currentDaily.expand
            notifyDataSetChanged()
        }
        holder.binding.txtRowDate.apply {
            text = TimeUtil().getCurrentDateTime(position.toLong())
        }
        holder.binding.expandedView.visibility = if (currentDaily.expand) View.VISIBLE else View.GONE //Expandable function to catch
        holder.binding.daily = currentDaily
    }

    fun updateList(newDataList: List<Daily>) {
        adapterDataList.clear()
        adapterDataList.addAll(newDataList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return adapterDataList.size
    }
}