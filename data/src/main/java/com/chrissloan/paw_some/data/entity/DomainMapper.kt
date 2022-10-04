package com.chrissloan.paw_some.data.entity

import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity
import com.chrissloan.paw_some.domain.entity.WeightDomainEntity

fun BreedApiEntity.toDomain() = BreedDomainEntity(
    id = id,
    name = name.orEmpty(),
    temperament = temperament.orEmpty(),
    lifeSpan = lifeSpan.orEmpty(),
    altNames = altNames.orEmpty(),
    wikiUrl = wikiUrl.orEmpty(),
    origin = origin.orEmpty(),
    weight = weight?.toDomain().orEmpty(),
    image = image?.toDomain().orEmpty(),
    countryCodes = countryCodes.orEmpty(),
    countryCode = countryCode.orEmpty(),
    description = description.orEmpty(),
    referenceImageId = referenceImageId.orEmpty(),
    indoor = indoor.orEmpty(),
    lap = lap.orEmpty(),
    adaptability = adaptability.orEmpty(),
    affectionLevel = affectionLevel.orEmpty(),
    childFriendly = childFriendly.orEmpty(),
    dogFriendly = dogFriendly.orEmpty(),
    energyLevel = energyLevel.orEmpty(),
    grooming = grooming.orEmpty(),
    healthIssues = healthIssues.orEmpty(),
    intelligence = intelligence.orEmpty(),
    sheddingLevel = sheddingLevel.orEmpty(),
    socialNeeds = socialNeeds.orEmpty(),
    strangerFriendly = strangerFriendly.orEmpty(),
    vocalisation = vocalisation.orEmpty(),
    experimental = experimental.orEmpty(),
    hairless = hairless.orEmpty(),
    natural = natural.orEmpty(),
    rare = rare.orEmpty(),
    rex = rex.orEmpty(),
    suppressedTail = suppressedTail.orEmpty(),
    shortLegs = shortLegs.orEmpty(),
    hypoallergenic = hypoallergenic.orEmpty()
)

fun WeightApiEntity.toDomain() =
    WeightDomainEntity(
        imperial = this.imperial,
        metric = this.metric
    )

fun ImageApiEntity.toDomain() =
    ImageDomainEntity(
        id = this.id,
        width = this.width,
        height = this.height,
        url = this.url
    )

private fun Int?.orEmpty() = this ?: 0
private fun ImageDomainEntity?.orEmpty() = this ?: ImageDomainEntity("", 0, 0, "")
private fun WeightDomainEntity?.orEmpty() = this ?: WeightDomainEntity("", "")
