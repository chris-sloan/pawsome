package com.chrissloan.paw_some.domain.usecase

import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.repository.BreedsRepository

class BreedDetailUseCase(
    private val breedsRepository: BreedsRepository
) {
    suspend operator fun invoke(breedId: String): BreedDomainEntity? =
        breedsRepository.getBreed(breedId)
}
