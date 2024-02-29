package com.example.synchrony.model

import com.google.gson.annotations.SerializedName

data class CountryDetail(
    @SerializedName("name"         ) var name         : Name?             = Name(),
    @SerializedName("independent"  ) var independent  : Boolean?          = null,
    @SerializedName("status"       ) var status       : String?           = null,
    @SerializedName("unMember"     ) var unMember     : Boolean?          = null,
    @SerializedName("capital"      ) var capital      : ArrayList<String> = arrayListOf(),
    @SerializedName("altSpellings" ) var altSpellings : ArrayList<String> = arrayListOf(),
    @SerializedName("region"       ) var region       : String?           = null,
    @SerializedName("subregion"    ) var subregion    : String?           = null,
    @SerializedName("latlng"       ) var latlng       : ArrayList<Double> = arrayListOf(),
    @SerializedName("landlocked"   ) var landlocked   : Boolean?          = null,
    @SerializedName("borders"      ) var borders      : ArrayList<String> = arrayListOf(),
    @SerializedName("area"         ) var area         : Double?           = null,
    @SerializedName("flag"         ) var flag         : String?           = null,
    @SerializedName("maps"         ) var maps         : Maps?             = Maps(),
    @SerializedName("population"   ) var population   : Int?              = null,
    @SerializedName("timezones"    ) var timezones    : ArrayList<String> = arrayListOf(),
    @SerializedName("continents"   ) var continents   : ArrayList<String> = arrayListOf(),
    @SerializedName("flags"        ) var flags        : Flags?            = Flags(),
    @SerializedName("startOfWeek"  ) var startOfWeek  : String?           = null,

)
