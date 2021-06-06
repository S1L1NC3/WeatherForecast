package com.dmd.weatherforecast.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dmd.weatherforecast.ApiConstants
import com.dmd.weatherforecast.R
import com.dmd.weatherforecast.databinding.ActivityMainBinding
import com.dmd.weatherforecast.util.PermissionUtil
import com.dmd.weatherforecast.util.TimeUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    @Named("apiKey")
    lateinit var apiKey: String

    @Inject
    lateinit var timeUtil: TimeUtil

    @Inject
    lateinit var permissionUtil: PermissionUtil

    private val viewModel: WeatherViewModel by viewModels()
    private var locationManager : LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        if (permissionUtil.isPermissionsGranted(context = applicationContext)){
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
            setupObserverAndOthers()
        } else {
            permissionUtil.requestPermission(context = this)
        }



        setContentView(binding.root)
    }


    @SuppressLint("MissingPermission")
    private fun setupObserverAndOthers(){
        //SuppressLint added cause of we already checked permission in PermissionUtil class
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 0f, locationListener)
        binding.activitySrlMain.setOnRefreshListener {
            viewModel.refreshData()
        }

        viewModel.responseWeather.observe(this, {
            actionBar.apply {
                title = it.timezone.substring((it.timezone.indexOf(ApiConstants.DIVIDER) + ApiConstants.DIVIDER_ADD_INDEX),it.timezone.length)
            }
            binding.activitySrlMain.isRefreshing = false
        })

        viewModel.responseSuccess.observe(this,{
            if (it){
                binding.activityRvMain.apply {
                    visibility = View.VISIBLE
                }
                binding.activityErrorMessageMain.apply {
                    visibility = View.GONE
                }
            } else {
                binding.activityErrorMessageMain.apply {
                    visibility = View.VISIBLE
                }
                binding.activityRvMain.apply {
                    visibility = View.GONE
                }
            }
        })

        binding.weatherViewModel = viewModel
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            viewModel.lat = location.latitude.toString()
            viewModel.lon = location.longitude.toString()
            viewModel.refreshData()

        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {
        }
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            ApiConstants.PERMISSION_LOCATION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (!permissionUtil.isPermissionsGranted(context = applicationContext)) {
                        Toast.makeText(this, resources.getString(R.string.permission_warning), Toast.LENGTH_SHORT).show()
                    } else {
                        setupObserverAndOthers()
                    }
                } else {
                    Toast.makeText(this, resources.getString(R.string.permission_warning), Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}