package com.chrissloan.paw_some.data.repository

import com.chrissloan.paw_some.data.service.CatApiService
import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
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
