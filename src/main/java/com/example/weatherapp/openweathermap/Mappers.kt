package com.example.weatherapp.openweathermap

import com.example.weatherapp.weather.Weather


fun mapOpenWeatherDataToWeather(weatherWrapper: WeatherWrapper) : Weather {
    val weather = weatherWrapper.weather.first()
    return Weather(
        description = weather.description,
        temperature = weatherWrapper.main.temp,
        humidity = weatherWrapper.main.humidity,
        pressure = weatherWrapper.main.pressure,
        iconUrl = "https://openweathermap.org/img/w/${weather.icon}.png"
    )
}