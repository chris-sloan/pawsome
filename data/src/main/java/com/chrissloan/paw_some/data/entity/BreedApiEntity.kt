package com.chrissloan.paw_some.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedApiEntity(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String?,
    @Json(name = "temperament") val temperament: String?,
    @Json(name = "life_span") val lifeSpan: String?,
    @Json(name = "alt_names") val altNames: String?,
    @Json(name = "wikipedia_url") val wikiUrl: String?,
    @Json(name = "origin") val origin: String?,
    @Json(name = "weight") val weight: WeightApiEntity?,
    @Json(name = "image") val image: ImageApiEntity?,
    @Json(name = "country_codes") val countryCodes: String?,
    @Json(name = "country_code") val countryCode: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "reference_image_id") val referenceImageId: String?,
    @Json(name = "indoor") val indoor: Int?,
    @Json(name = "lap") val lap: Int?,
    @Json(name = "adaptability") val adaptability: Int?,
    @Json(name = "affection_level") val affectionLevel: Int?,
    @Json(name = "child_friendly") val childFriendly: Int?,
    @Json(name = "dog_friendly") val dogFriendly: Int?,
    @Json(name = "energy_level") val energyLevel: Int?,
    @Json(name = "grooming") val grooming: Int?,
    @Json(name = "health_issues") val healthIssues: Int?,
    @Json(name = "intelligence") val intelligence: Int?,
    @Json(name = "shedding_level") val sheddingLevel: Int?,
    @Json(name = "social_needs") val socialNeeds: Int?,
    @Json(name = "stranger_friendly") val strangerFriendly: Int?,
    @Json(name = "vocalisation") val vocalisation: Int?,
    @Json(name = "experimental") val experimental: Int?,
    @Json(name = "hairless") val hairless: Int?,
    @Json(name = "natural") val natural: Int?,
    @Json(name = "rare") val rare: Int?,
    @Json(name = "rex") val rex: Int?,
    @Json(name = "suppressed_tail") val suppressedTail: Int?,
    @Json(name = "short_legs") val shortLegs: Int?,
    @Json(name = "hypoallergenic") val hypoallergenic: Int?,
)

@JsonClass(generateAdapter = true)
data class WeightApiEntity(
    @Json(name = "imperial") val imperial: String?,
    @Json(name = "metric") val metric: String?
)

@JsonClass(generateAdapter = true)
data class ImageApiEntity(
    @Json(name = "id") val id: String,
    @Json(name = "width") val width: Int?,
    @Json(name = "height") val height: Int?,
    @Json(name = "url") val url: String?,
)

