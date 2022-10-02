package com.chrissloan.paw_some.data.repository

import com.chrissloan.paw_some.data.service.CatApiService
import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity
import com.chrissloan.paw_some.domain.entity.WeightDomainEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class BreedsRepositoryImplTest {

    private val catApiService: CatApiService = mockk()
    private lateinit var sut: BreedsRepositoryImpl

    @Test
    fun `given catApiService returns emptyList, when service is called, then return Content with an empty list`() =
        runBlocking {
            coEvery { catApiService.getAllBreeds() } returns emptyList()
            sut = BreedsRepositoryImpl(catApiService)

            val result = sut.getAllBreeds()

            assertTrue { result is DomainResponse.Content<List<BreedDomainEntity>> }
            assertTrue { (result as DomainResponse.Content<List<BreedDomainEntity>>).result.isEmpty() }
        }

    @Test
    fun `given catApi returns items, when service is called, then return a list of items`() =
        runBlocking {
            val domainEntityList = createBreedDomainEntityList(6)
            coEvery { catApiService.getAllBreeds() } returns domainEntityList
            sut = BreedsRepositoryImpl(catApiService)

            val result = sut.getAllBreeds()

            assertTrue { result is DomainResponse.Content<List<BreedDomainEntity>> }
            assertTrue { (result as DomainResponse.Content<List<BreedDomainEntity>>).result.size == domainEntityList.size }
        }

    @Test
    fun `given catApiService returns error, when service is called, then DomainResponse Error returned`() =
        runBlocking {
            coEvery { catApiService.getAllBreeds() } throws IllegalArgumentException()
            sut = BreedsRepositoryImpl(catApiService)

            val result = sut.getAllBreeds()

            assertTrue { result is DomainResponse.Error }
        }

    private fun createBreedDomainEntityList(number: Int): List<BreedDomainEntity> {
        val list = mutableListOf<BreedDomainEntity>()
        for (i in 0 until number) {
            list.add(
                BreedDomainEntity(
                    id = "item_$i",
                    name = "name_$i",
                    temperament = "temperament",
                    lifeSpan = "lifeSpan",
                    altNames = "altNames",
                    wikiUrl = "wikiUrl",
                    origin = "origin",
                    weight = WeightDomainEntity(imperial = "imperial", metric = "metric"),
                    image = ImageDomainEntity(id = "imageId_$i", width = 200, height = 200, url = "url"),
                    countryCodes = "countryCodes",
                    countryCode = "countryCode",
                    description = "description",
                    referenceImageId = "referenceImageId",
                    indoor = 1,
                    lap = 1,
                    adaptability = 1,
                    affectionLevel = 1,
                    childFriendly = 1,
                    dogFriendly = 1,
                    energyLevel = 1,
                    grooming = 1,
                    healthIssues = 1,
                    intelligence = 1,
                    sheddingLevel = 1,
                    socialNeeds = 1,
                    strangerFriendly = 1,
                    vocalisation = 1,
                    experimental = 1,
                    hairless = 1,
                    natural = 1,
                    rare = 1,
                    rex = 1,
                    suppressedTail = 1,
                    shortLegs = 1,
                    hypoallergenic = 1
                )
            )
        }
        return list
    }
}
