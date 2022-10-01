package com.chrissloan.paw_some.data.service

import com.chrissloan.paw_some.data.api.CatApi
import com.chrissloan.paw_some.data.entity.BreedApiEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class CatApiServiceTest {

    private val catApi: CatApi = mockk()
    private lateinit var sut: CatApiService

    @Test
    fun `given catApi returns emptyList, when service is called, then return an empty list`() =
        runBlocking {
            coEvery { catApi.getBreeds() } returns emptyList()
            sut = CatApiService(catApi)

            val result = sut.getAllBreeds()

            assertTrue { result.isEmpty() }
        }

    @Test
    fun `given catApi returns items, when service is called, then return a list of items`() =
        runBlocking {
            val apiResponseList = createBreedApiEntityList(6)
            coEvery { catApi.getBreeds() } returns apiResponseList
            sut = CatApiService(catApi)

            val result = sut.getAllBreeds()

            assertTrue { result.isNotEmpty() }
            assertTrue { result.size == apiResponseList.size }

            //TODO continue to verify the mapping behaviour here / or in another test
        }

    @Test(expected = IllegalArgumentException::class)
    fun `given catApi returns error, when service is called, then error propogated`() {
        runBlocking {
            coEvery { catApi.getBreeds() } throws IllegalArgumentException()
            sut = CatApiService(catApi)

            sut.getAllBreeds()

        }
    }

    private fun createBreedApiEntityList(number: Int): List<BreedApiEntity> {
        val list = mutableListOf<BreedApiEntity>()
        for (i in 0 until number) {
            list.add(
                BreedApiEntity(
                    id = "item_$i",
                    name = "name_$i",
                    temperament = "calm",
                    lifeSpan = "donkeys",
                    altNames = "othername, shortername",
                    wikiUrl = "www.testurl.com",
                    origin = "",
                    weightImperial = "6lbs",
                )
            )
        }
        return list
    }
}
