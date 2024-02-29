package com.example.synchrony.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.synchrony.model.Country
import com.example.synchrony.model.CountryDetail
import com.example.synchrony.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    private var allCountriesMutableLiveData= MutableLiveData<List<Country>> ()

    val allCountriesLiveData: LiveData<List<Country>>
        get() = allCountriesMutableLiveData

    private var specificCountriesMutableLiveData= MutableLiveData<List<CountryDetail>> ()

    val specificCountriesLiveData: LiveData<List<CountryDetail>>
        get() = specificCountriesMutableLiveData

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val results= mainRepository.getAllCountries("name", "flags")
            if(results!= null && results.isSuccessful) {
                //Log.d("raj", "getImagesPexel: msg=" +results.body())
                allCountriesMutableLiveData.postValue(results.body())

            }
            else {
                //Todo : handle error as per ur requirement
                Log.d("raj", "getImagesPexel: failure")
            }
        }
    }
    fun getSpecificCountries( countryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val results= mainRepository.getSpecificCountry(countryName)
            if(results!= null && results.isSuccessful) {
                //Log.d("raj", "getSpecificCountries: msg=" +results.body())
               specificCountriesMutableLiveData.postValue(results.body())

            }
            else {
                //Todo : handle error as per ur requirement
                Log.d("raj", "getSpecificCountries: failure")
            }
        }
    }
}