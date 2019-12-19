package com.example.weatherapp.city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.App
import com.example.weatherapp.R

class CityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        App.database
    }
}
