package com.chrissloan.paw_some.data.service

import com.chrissloan.paw_some.data.api.CatApi
import com.chrissloan.paw_some.data.entity.toDomain
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity

class CatApiService(
    private val catApi: CatApi
) {
    suspend fun getAllBreeds(): List<BreedDomainEntity> = catApi.getBreeds().map { it.toDomain() }
}
