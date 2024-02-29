package com.example.synchrony.network

import com.example.synchrony.model.Country
import com.example.synchrony.model.CountryDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CountryApiService {

    @GET("all")
    suspend fun getAllCountries(@Query("fields") name: String, @Query("fields") flags: String): Response<List<Country>>

    @GET("name/{countryName}")
    suspend fun getSpecificCountry(@Path("countryName") name: String) : Response<List<CountryDetail>>
}