package com.tc.countrieslist.data.remote

import com.tc.countrieslist.data.model.countries.CountriesItemModel
import com.tc.countrieslist.data.remote.ApiDetails
import retrofit2.Response
import retrofit2.http.GET

interface CountriesService {
    @GET(ApiDetails.COUNTRIES_ENDPOINT)
    suspend fun getCountries(): Response<ArrayList<CountriesItemModel>>
}