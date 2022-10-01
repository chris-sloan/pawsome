package com.chrissloan.paw_some.domain.usecase

import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.repository.BreedsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertTrue
import kotlinx.coroutines.runBlocking

import org.junit.Test


class FetchBreedsUseCaseTest {

    private val breedsRepository: BreedsRepository = mockk()
    private lateinit var sut: FetchBreedsUseCase

    @Test
    fun `given repository returns Loading, when use case invoked, then return Loading`() =
        runBlocking {
            coEvery { breedsRepository.getAllBreeds() } returns mockk<DomainResponse.Loading>()
            sut = FetchBreedsUseCase(breedsRepository)

            val result = sut()

            assertTrue { result is DomainResponse.Loading }
        }

    @Test
    fun `given repository returns Content, when use case invoked, then return Content`() =
        runBlocking {
            coEvery { breedsRepository.getAllBreeds() } returns mockk<DomainResponse.Content<List<BreedDomainEntity>>>()
            sut = FetchBreedsUseCase(breedsRepository)

            val result = sut()

            assertTrue { result is DomainResponse.Content }
        }

    @Test
    fun `given repository returns Error, when use case invoked, then return Error`() =
        runBlocking {
            coEvery { breedsRepository.getAllBreeds() } returns mockk<DomainResponse.Error>()
            sut = FetchBreedsUseCase(breedsRepository)

            val result = sut()

            assertTrue { result is DomainResponse.Error }
        }
}
