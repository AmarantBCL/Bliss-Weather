package com.amarant.apps.blissweather.repository

import com.amarant.apps.blissweather.server.ApiServices

class CityRepository(val api: ApiServices) {

    fun getCities(q: String, limit: Int) =
        api.getCitiesList(q, limit, "c568f307d773a4775ed5a815d982cd28")
}