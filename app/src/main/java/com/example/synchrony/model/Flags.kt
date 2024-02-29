package com.example.synchrony.model

import com.google.gson.annotations.SerializedName

data class Flags(
    @SerializedName("png" ) var png : String? = null,
    @SerializedName("svg" ) var svg : String? = null,
    @SerializedName("alt" ) var alt : String? = null
)
