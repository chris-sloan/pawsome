package com.chrissloan.paw_some.presentation.screen.breeddetail

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrissloan.paw_some.domain.core.DomainResponse
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity
import com.chrissloan.paw_some.domain.usecase.BreedDetailUseCase
import com.chrissloan.paw_some.domain.usecase.BreedImagesUseCase
import kotlinx.coroutines.launch

class BreedDetailViewModel(
    breedId: String,
    private val breedDetail: BreedDetailUseCase,
    private val breedImages: BreedImagesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(BreedDetailUiState())
        private set

    init {
        fetchBreedDetail(breedId)
        fetchBreedImages(breedId)
    }

    private fun fetchBreedImages(breedId: String) {
        viewModelScope.launch {
            uiState = when (val domainResponse = breedImages(breedId)) {
                DomainResponse.Loading -> uiState.copy(isLoading = false)
                is DomainResponse.Content -> uiState.copy(
                    isLoading = false,
                    images = domainResponse.result
                )
                is DomainResponse.Error -> uiState.copy(
                    isLoading = false,
                    errorMessage = domainResponse.exception.message
                )
            }
        }
    }

    private fun fetchBreedDetail(breedId: String) {
        viewModelScope.launch {
            val breedDetail = breedDetail(breedId)
            uiState = uiState.copy(isLoading = false, breed = breedDetail)
        }
    }


    fun handleAction(action: BreedDetailAction) {
        // TODO
    }

    @Stable
    data class BreedDetailUiState(
        val isLoading: Boolean = true,
        val breed: BreedDomainEntity? = null,
        val images: List<ImageDomainEntity> = emptyList(),
        val errorMessage: String? = null,
    )

    @Stable
    sealed class BreedDetailAction {
        object ErrorMessageShown : BreedDetailAction()
        data class ImageSelected(val image: BreedImageDomainEntity) : BreedDetailAction()
    }

    // TODO move to damain.
    class BreedImageDomainEntity
}
