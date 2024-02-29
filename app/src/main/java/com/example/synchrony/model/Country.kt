package com.example.synchrony.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("flags" ) var flags : Flags? = Flags(),
    @SerializedName("name"  ) var name  : Name?  = Name()
)
