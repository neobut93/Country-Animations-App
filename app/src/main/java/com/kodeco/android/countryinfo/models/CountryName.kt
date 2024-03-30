package com.kodeco.android.countryinfo.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryName(val common: String)
