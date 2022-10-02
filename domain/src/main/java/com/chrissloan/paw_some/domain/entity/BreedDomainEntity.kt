package com.chrissloan.paw_some.domain.entity

data class BreedDomainEntity(
    val id: String,
    val name: String,
    val temperament: String,
    val lifeSpan: String,
    val altNames: String,
    val wikiUrl: String,
    val origin: String,
    val weight: WeightDomainEntity,
    val image: ImageDomainEntity,
    val countryCodes: String,
    val countryCode: String,
    val description: String,
    val referenceImageId: String,
    val indoor: Int,
    val lap: Int,
    val adaptability: Int,
    val affectionLevel: Int,
    val childFriendly: Int,
    val dogFriendly: Int,
    val energyLevel: Int,
    val grooming: Int,
    val healthIssues: Int,
    val intelligence: Int,
    val sheddingLevel: Int,
    val socialNeeds: Int,
    val strangerFriendly: Int,
    val vocalisation: Int,
    val experimental: Int,
    val hairless: Int,
    val natural: Int,
    val rare: Int,
    val rex: Int,
    val suppressedTail: Int,
    val shortLegs: Int,
    val hypoallergenic: Int,
)

data class WeightDomainEntity(
    val imperial: String?,
    val metric: String?
)

data class ImageDomainEntity(
    val id: String,
    val width: Int?,
    val height: Int?,
    val url: String?,
)
