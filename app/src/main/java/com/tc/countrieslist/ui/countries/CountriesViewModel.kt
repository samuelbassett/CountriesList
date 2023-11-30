package com.tc.countrieslist.ui.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.countrieslist.data.model.countries.CountriesItemModel
import com.tc.countrieslist.data.remote.ApiDetails
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {

    init {
        getCountries()
    }

    private val _countryData =
        MutableLiveData<UIState<ArrayList<CountriesItemModel>>>(UIState.Loading)
    val countryData = _countryData

    fun getCountries() {
        viewModelScope.launch {
            val result = ApiDetails.service.getCountries()
            _countryData.postValue(UIState.Loading)
            if (_countryData.value !is UIState.Success) {
                if (result.isSuccessful) {
                    _countryData.postValue(UIState.Success(result.body()!!))
                } else {
                    _countryData.postValue(UIState.Error(result.message()))
                }
            }
        }
    }
}