package com.chrissloan.paw_some.domain.usecase

import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity
import com.chrissloan.paw_some.domain.repository.BreedsRepository

class BreedImagesUseCase(
    private val breedsRepository: BreedsRepository
) {
    suspend operator fun invoke(breedId: String): DomainResponse<List<ImageDomainEntity>> =
        breedsRepository.getImages(breedId)
}
