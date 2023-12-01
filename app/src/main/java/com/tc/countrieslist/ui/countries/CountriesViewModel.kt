package com.tc.countrieslist.ui.countries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.tc.countrieslist.data.model.countries.CountriesItemModel
import com.tc.countrieslist.data.remote.ApiDetails
import com.tc.countrieslist.utils.NetworkUtils

class CountriesViewModel(application: Application) : AndroidViewModel(application) {

    private val _countryData: LiveData<UIState<ArrayList<CountriesItemModel>>> = liveData {
        emit(UIState.Loading)
        if (NetworkUtils.isNetworkAvailable(getApplication())) {
            val result = ApiDetails.service.getCountries()
            if (result.isSuccessful) {
                emit(UIState.Success(result.body()!!))
            } else {
                emit(UIState.Error(result.message()))
            }
        } else {
            emit(UIState.Error("Network Unavailable"))
        }
    }

    val countryData = _countryData
}