package com.chrissloan.paw_some.domain.repository

import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity

interface BreedsRepository {
    suspend fun getAllBreeds(): DomainResponse<List<BreedDomainEntity>>
}
