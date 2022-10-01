package com.chrissloan.paw_some.data.repository

import com.chrissloan.paw_some.data.extension.domainResponse
import com.chrissloan.paw_some.data.service.CatApiService
import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.repository.BreedsRepository

class BreedsRepositoryImpl(
    private val apiService: CatApiService
) : BreedsRepository {
    override suspend fun getAllBreeds(): DomainResponse<List<BreedDomainEntity>> =
        domainResponse {
            apiService.getAllBreeds()
        }
}
