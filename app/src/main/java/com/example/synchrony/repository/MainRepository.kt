package com.example.synchrony.repository

import com.example.synchrony.network.CountryApiService

class MainRepository(val countryApiService: CountryApiService) {

    suspend fun getAllCountries(name: String, flags: String) = countryApiService.getAllCountries(name, flags)

    suspend fun getSpecificCountry(name: String) = countryApiService.getSpecificCountry(name)
}