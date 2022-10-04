package com.chrissloan.paw_some.data.repository

import com.chrissloan.paw_some.data.extension.domainResponse
import com.chrissloan.paw_some.data.service.CatApiService
import com.chrissloan.paw_some.data.utils.TimeUtils
import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity
import com.chrissloan.paw_some.domain.repository.BreedsRepository

class BreedsRepositoryImpl(
    private val apiService: CatApiService,
    private val timeUtils: TimeUtils
) : BreedsRepository {

    private var cache: MemoryCache? = null
    private val cacheLength = timeUtils.minutesMs(10)

    override suspend fun getAllBreeds(): DomainResponse<List<BreedDomainEntity>> {
        if (isCacheExpired()) {
            fetchBreedsFromApi()
        }
        return domainResponse { cache?.items ?: emptyList() }
    }

    // TODO create cache here also
    override suspend fun getImages(breedId: String): DomainResponse<List<ImageDomainEntity>> =
        domainResponse {
            apiService.getBreedImages(breedId)
        }

    override suspend fun getBreed(breedId: String): BreedDomainEntity? {
        if (isCacheExpired()) {
            fetchBreedsFromApi()
        }
        return cache?.items?.firstOrNull { it.id == breedId }
    }

    private suspend fun fetchBreedsFromApi():  List<BreedDomainEntity> {
        val allBreeds = try {
            apiService.getAllBreeds()
        } catch (e: Exception) {
            throw e
        }
        cache = MemoryCache(timeUtils.currentTime(), allBreeds)
        return allBreeds
    }

    private fun isCacheExpired(): Boolean = cache?.let { (timeUtils.currentTime() - it.timeStamp) > cacheLength } ?: true

    data class MemoryCache(
        val timeStamp: Long,
        val items: List<BreedDomainEntity>
    )
}
