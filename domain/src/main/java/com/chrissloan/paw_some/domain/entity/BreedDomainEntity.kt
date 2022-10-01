package com.chrissloan.paw_some.domain.entity

data class BreedDomainEntity(
    val id: String,
    val name: String,
    val temperament: String,
    val lifeSpan: String,
    val altNames: String,
    val wikiUrl: String,
    val origin: String,
    val weightImperial: String
)
