package com.dmd.weatherforecast.util

import android.os.Build
import com.dmd.weatherforecast.ApiConstants
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TimeUtil {
    fun getCurrentDateTime(plusDays: Long)  : String{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return when {
                plusDays.toInt() == ApiConstants.DEFAULT_PLUS_DAYS -> {
                    ApiConstants.TODAY
                } plusDays.toInt() == ApiConstants.TOMORROW_PLUS_DAYS -> {
                    ApiConstants.TOMORROW
                } else -> {
                    LocalDateTime.now().plusDays(plusDays).format(DateTimeFormatter.ofPattern(ApiConstants.PATTERN))
                }
            }
        } else {
            val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
            val currentDateAndTime: String = simpleDateFormat.format(Date())

            val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val formattedDate = formatter.format(parser.parse("source"))
            ""
        }
    }

}