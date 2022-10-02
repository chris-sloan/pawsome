package com.chrissloan.paw_some.data.service

import com.chrissloan.paw_some.data.api.ApiFactory
import com.chrissloan.paw_some.data.api.CatApi
import com.chrissloan.paw_some.data.entity.BreedApiEntity
import com.chrissloan.paw_some.data.entity.ImageApiEntity
import com.chrissloan.paw_some.data.entity.WeightApiEntity
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class CatApiServiceTest {

    private val catApi: CatApi = mockk()

    private val apiFactory: ApiFactory = mockk {
        every { create() } returns catApi
    }

    private lateinit var sut: CatApiService

    @Test
    fun `given catApi returns emptyList, when service is called, then return an empty list`() =
        runBlocking {
            coEvery { catApi.getBreeds() } returns emptyList()

            sut = CatApiService(apiFactory)

            val result = sut.getAllBreeds()

            assertTrue { result.isEmpty() }
        }

    @Test
    fun `given catApi returns items, when service is called, then return a list of items`() =
        runBlocking {
            val apiResponseList = createBreedApiEntityList(6)
            coEvery { catApi.getBreeds() } returns apiResponseList
            sut = CatApiService(apiFactory)

            val result = sut.getAllBreeds()

            assertTrue { result.isNotEmpty() }
            assertTrue { result.size == apiResponseList.size }

            //TODO continue to verify the mapping behaviour here / or in another test
        }

    @Test(expected = IllegalArgumentException::class)
    fun `given catApi returns error, when service is called, then error propogated`() {
        runBlocking {
            coEvery { catApi.getBreeds() } throws IllegalArgumentException()
            sut = CatApiService(apiFactory)

            sut.getAllBreeds()

        }
    }

    private fun createBreedApiEntityList(number: Int): List<BreedApiEntity> {
        val list = mutableListOf<BreedApiEntity>()
        for (i in 0 until number) {
            list.add(
                BreedApiEntity(
                    id = "id_$i",
                    name = "name_$i",
                    temperament = "temperament",
                    lifeSpan = "lifeSpan",
                    altNames = "altNames",
                    wikiUrl = "wikiUrl",
                    origin = "origin",
                    weight = WeightApiEntity(imperial = "imperial", metric = "metric"),
                    image = ImageApiEntity(id = "imageId_$i", width = 200, height = 200, url = "url"),
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
