package com.chrissloan.paw_some.data.entity

import com.squareup.moshi.Json

data class BreedApiEntity(
    val id: String,
    val name: String,
    val temperament: String,
    @Json(name = "life_span") val lifeSpan: String,
    @Json(name = "alt_names") val altNames: String,
    @Json(name = "wikipedia_url") val wikiUrl: String,
    val origin: String,
    @Json(name = "weight_imperial") val weightImperial: String
)
