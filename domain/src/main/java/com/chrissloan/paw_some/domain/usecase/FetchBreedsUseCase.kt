package com.chrissloan.paw_some.domain.usecase

import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.repository.BreedsRepository

class FetchBreedsUseCase(
    private val breedsRepository: BreedsRepository
) {
    suspend operator fun invoke(): DomainResponse<List<BreedDomainEntity>> =
        breedsRepository.getAllBreeds()
}
