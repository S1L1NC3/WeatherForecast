package com.dmd.weatherforecast.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dmd.weatherforecast.ApiConstants

class PermissionUtil {

    private var permissionsToGet = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    fun isPermissionsGranted(context: Context) : Boolean {
        return context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(context:  AppCompatActivity){
        ActivityCompat.requestPermissions(context, permissionsToGet, ApiConstants.PERMISSION_LOCATION_REQUEST_CODE)
    }


}