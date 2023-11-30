package com.tc.countrieslist.data.model.countries


import com.google.gson.annotations.SerializedName

data class LanguageModel(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("iso639_2")
    val iso6392: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("nativeName")
    val nativeName: String? = ""
)