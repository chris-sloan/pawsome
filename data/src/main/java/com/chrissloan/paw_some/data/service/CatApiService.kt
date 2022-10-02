package com.chrissloan.paw_some.data.service

import com.chrissloan.paw_some.data.api.ApiFactory
import com.chrissloan.paw_some.data.api.CatApi
import com.chrissloan.paw_some.data.entity.toDomain
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity

class CatApiService(
    apiFactory: ApiFactory
) {
    private val catApi: CatApi = apiFactory.create()

    suspend fun getAllBreeds(): List<BreedDomainEntity> = catApi.getBreeds().map { it.toDomain() }

}
