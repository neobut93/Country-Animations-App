package com.kodeco.android.countryinfo.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    val name: CountryName,
    val capital: List<String>?,
    val population: Long,
    val area: Float,
    val flags: CountryFlags,
    val isFavorite: Boolean = false,
) {
    val mainCapital = capital?.firstOrNull() ?: "N/A"
    val commonName = name.common
    val flagUrl = flags.png
}
