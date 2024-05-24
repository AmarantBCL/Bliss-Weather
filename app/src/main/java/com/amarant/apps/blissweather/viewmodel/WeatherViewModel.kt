package com.amarant.apps.blissweather.viewmodel

import androidx.lifecycle.ViewModel
import com.amarant.apps.blissweather.repository.WeatherRepository
import com.amarant.apps.blissweather.server.ApiClient
import com.amarant.apps.blissweather.server.ApiServices

class WeatherViewModel(val repository: WeatherRepository) : ViewModel() {

    constructor() : this(WeatherRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCurrentWeather(lat: Double, lon: Double, unit: String) =
        repository.getCurrentWeather(lat, lon, unit)

    fun loadForecastWeather(lat: Double, lon: Double, unit: String) =
        repository.getForecastWeather(lat, lon, unit)
}