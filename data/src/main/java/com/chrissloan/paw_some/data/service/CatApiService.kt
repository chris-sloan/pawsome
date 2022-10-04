package com.chrissloan.paw_some.data.service

import com.chrissloan.paw_some.data.api.ApiFactory
import com.chrissloan.paw_some.data.api.CatApi
import com.chrissloan.paw_some.data.entity.toDomain
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity

class CatApiService(
    apiFactory: ApiFactory
) {
    private val catApi: CatApi = apiFactory.create()

    suspend fun getAllBreeds(): List<BreedDomainEntity> = catApi.getBreeds().map { it.toDomain() }

    suspend fun getBreedImages(breedId: String): List<ImageDomainEntity> = catApi.getImages(breedId).map { it.toDomain() }
}
