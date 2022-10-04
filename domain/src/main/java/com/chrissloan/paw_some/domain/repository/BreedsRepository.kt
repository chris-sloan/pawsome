package com.chrissloan.paw_some.domain.repository

import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity

interface BreedsRepository {
    suspend fun getAllBreeds(): DomainResponse<List<BreedDomainEntity>>

    suspend fun getImages(breedId: String): DomainResponse<List<ImageDomainEntity>>

    suspend fun getBreed(breedId: String): BreedDomainEntity?

}
