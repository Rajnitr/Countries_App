package com.example.synchrony.model

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common"     ) var common     : String?     = null,
    @SerializedName("official"   ) var official   : String?     = null
)
