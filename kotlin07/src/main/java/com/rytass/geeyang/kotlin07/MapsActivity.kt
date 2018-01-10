package com.rytass.geeyang.kotlin07

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_maps.*
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    // Location Settings APIs.
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    var settingsClient: SettingsClient? = null
    var locationSettingsRequest: LocationSettingsRequest? = null
    var locationCallback: LocationCallback? = null
    var locationRequest: LocationRequest? = null
    var location: Location? = null

    private var priority = 0

    val REQUEST_CHECK_SETTINGS = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        initial()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        startLocationUpdates()
    }

    private fun initial() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        settingsClient = LocationServices.getSettingsClient(this)

        initialLocationSetting()
    }

    private fun initialLocationSetting() {
        createLocationCallback()
        createLocationRequest()
        buildLocationSettingsRequest()
    }

    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                location = p0?.lastLocation

                stopLocationUpdates()

                coordinate.text = "${location?.latitude}, ${location?.longitude}"

                val markerOptions = MarkerOptions()
                        .position(LatLng(location!!.latitude, location!!.longitude))

                mMap.addMarker(markerOptions)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location!!.latitude, location!!.longitude), 16f))
            }
        }
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()

        priority = 1

        when (priority) {
            0 -> locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            1 -> locationRequest!!.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
            2 -> locationRequest!!.priority = LocationRequest.PRIORITY_LOW_POWER
            else -> locationRequest!!.priority = LocationRequest.PRIORITY_NO_POWER
        }

        locationRequest!!.interval = 60000
        locationRequest!!.fastestInterval = 5000
    }

    private fun buildLocationSettingsRequest() {
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest!!)
        locationSettingsRequest = builder.build()
    }

    private fun startLocationUpdates() {
        settingsClient?.checkLocationSettings(locationSettingsRequest)
                ?.addOnSuccessListener(this, object : OnSuccessListener<LocationSettingsResponse> {
                    @SuppressLint("MissingPermission")
                    override fun onSuccess(p0: LocationSettingsResponse?) {
                        fusedLocationProviderClient?.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
                    }
                })
                ?.addOnFailureListener(this, object: OnFailureListener{
                    override fun onFailure(p0: Exception) {
                        p0 as ApiException
                        when(p0.statusCode) {
                            LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                                val rae = p0 as ResolvableApiException
                                rae.startResolutionForResult(this@MapsActivity, REQUEST_CHECK_SETTINGS)
                            }
                        }
                    }
                })
    }

    private fun stopLocationUpdates() {
        fusedLocationProviderClient?.removeLocationUpdates(locationCallback)
    }
}
