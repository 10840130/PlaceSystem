package com.example.place_system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat


class MainActivity2 : AppCompatActivity()  {

    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    //private val locationPermissionCode = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        title = "KotlinApp"
        val single_location1 =findViewById<Button>(R.id.single_location1)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {return}

        val location = Location(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER))
        single_location1.setOnClickListener {
            showLocation(location)
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,1F,locationListener)

        /*single_location1.setOnClickListener {
            getLocation()
        }*/
    }

    @SuppressLint("SetTextI18n")
    private fun showLocation(location: Location){
        tvGpsLocation = findViewById(R.id.single_gps_view1)
        tvGpsLocation.text = "Latitude: " + location.latitude.toString() + " , Longitude: " + location.longitude.toString()
    }
    private val locationListener: LocationListener = object : LocationListener{
        override fun onLocationChanged(location: Location) {
            showLocation(location)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderDisabled(provider: String) {

        }

        override fun onProviderEnabled(provider: String) {

        }
    }


   /* private fun getLocation() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)

        //val localGpsLocation : Location
      //  localGpsLocation= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

    }
    @SuppressLint("SetTextI18n")
    override fun onLocationChanged(location: Location){
        tvGpsLocation = findViewById(R.id.single_gps_view1)
        tvGpsLocation.text = "Latitude: " + location.latitude.toString() + " , Longitude: " + location.longitude.toString()



    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }*/
}