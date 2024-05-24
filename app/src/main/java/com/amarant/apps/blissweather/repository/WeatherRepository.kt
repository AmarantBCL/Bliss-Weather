package com.amarant.apps.blissweather.repository

import com.amarant.apps.blissweather.server.ApiServices

class WeatherRepository(val api: ApiServices) {

    fun getCurrentWeather(lat: Double, lon: Double, unit: String) =
        api.getCurrentWeather(lat, lon, unit, "c568f307d773a4775ed5a815d982cd28")

    fun getForecastWeather(lat: Double, lon: Double, unit: String) =
        api.getForecastWeather(lat, lon, unit, "c568f307d773a4775ed5a815d982cd28")
}