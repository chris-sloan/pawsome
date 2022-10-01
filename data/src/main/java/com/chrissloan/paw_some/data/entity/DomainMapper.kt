package com.chrissloan.paw_some.data.entity

import com.chrissloan.paw_some.domain.entity.BreedDomainEntity

fun BreedApiEntity.toDomain() = BreedDomainEntity(
    id = id,
    name = name,
    temperament = temperament,
    lifeSpan = lifeSpan,
    altNames = altNames,
    wikiUrl = wikiUrl,
    origin = origin,
    weightImperial = weightImperial
)
