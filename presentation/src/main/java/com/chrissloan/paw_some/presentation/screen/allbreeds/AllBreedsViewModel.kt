package com.chrissloan.paw_some.presentation.screen.allbreeds

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrissloan.paw_some.domain.core.DomainResponse.Content
import com.chrissloan.paw_some.domain.core.DomainResponse.Error
import com.chrissloan.paw_some.domain.core.DomainResponse.Loading
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.domain.usecase.FetchBreedsUseCase
import com.chrissloan.paw_some.presentation.R
import kotlinx.coroutines.launch

@Stable
class AllBreedsViewModel(
    private val fetchBreeds: FetchBreedsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(AllBreedsUiState())
        private set

    init {
        fetchAllBreeds()
    }

    private fun fetchAllBreeds() {
        viewModelScope.launch {
            uiState = try {
                when (val response = fetchBreeds()) {
                    Loading -> uiState.copy(isLoading = true)
                    is Content -> uiState.copy(isLoading = false, breeds = response.result)
                    is Error -> uiState.copy(
                        isLoading = false,
                        errorMessageId = DEFAULT_ERROR_MESSAGE
                    )
                }
            } catch (e: Exception) {
                uiState.copy(isLoading = false, errorMessageId = DEFAULT_ERROR_MESSAGE)
            }
        }
    }

    fun applyFilter() {
        /**
         * We don't handle any filters yet, so let the user know,
         * but do nothing else
         */

        uiState = uiState.copy(
            errorMessageId = DEFAULT_FILTER_NOT_ACTIVE_MESSAGE
        )
    }

    fun handleBreedSelectionAction(breedDomainEntity: BreedDomainEntity) {
        uiState = uiState.copy(
            navigationEvent = AllBreedsNavigationEvent.ShowBreed(breedDomainEntity)
        )
    }

    fun handleAction(action: AllBreedsAction) {
        uiState = when (action) {
            is AllBreedsAction.BreedSelected -> uiState.copy(
                navigationEvent = AllBreedsNavigationEvent.ShowBreed(action.breed)
            )
            AllBreedsAction.ErrorMessageShown -> uiState.copy(errorMessageId = null)
        }
    }

    fun navigationEventComplete() {
        uiState = uiState.copy(navigationEvent = null)
    }

    @Stable
    data class AllBreedsUiState(
        val isLoading: Boolean = true,
        val breeds: List<BreedDomainEntity> = emptyList(),
        @StringRes val errorMessageId: Int? = null,
        val navigationEvent: AllBreedsNavigationEvent? = null
    )

    @Stable
    sealed class AllBreedsAction {
        object ErrorMessageShown : AllBreedsAction()
        data class BreedSelected(val breed: BreedDomainEntity) : AllBreedsAction()
    }

    @Stable
    sealed class AllBreedsNavigationEvent {
        data class ShowBreed(val breed: BreedDomainEntity) : AllBreedsNavigationEvent()
    }

    companion object {
        private val DEFAULT_ERROR_MESSAGE = R.string.error_default_message
        private val DEFAULT_FILTER_NOT_ACTIVE_MESSAGE = R.string.error_filter_not_active
    }
}
