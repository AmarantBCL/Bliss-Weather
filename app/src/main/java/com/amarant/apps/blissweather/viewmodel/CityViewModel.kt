package com.amarant.apps.blissweather.viewmodel

import androidx.lifecycle.ViewModel
import com.amarant.apps.blissweather.repository.CityRepository
import com.amarant.apps.blissweather.server.ApiClient
import com.amarant.apps.blissweather.server.ApiServices

class CityViewModel(val repository: CityRepository) : ViewModel() {

    constructor(): this(CityRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCity(q: String, limit: Int) =
        repository.getCities(q, limit)
}