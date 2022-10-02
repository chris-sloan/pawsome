package com.chrissloan.paw_some.presentation.screen

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
import kotlinx.coroutines.launch

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
                        errorMessage = response.exception.message ?: DEFAULT_ERROR_MESSAGE
                    )
                }
            } catch (e: Exception) {
                uiState.copy(isLoading = false, errorMessage = DEFAULT_ERROR_MESSAGE)
            }
        }
    }

    fun handleAction(action: AllBreedsAction) {
        when (action) {
            is AllBreedsAction.BreedSelected -> TODO()
            AllBreedsAction.ErrorMessageShown -> uiState = uiState.copy(errorMessage = null)
        }
    }

    data class AllBreedsUiState(
        val isLoading: Boolean = true,
        val breeds: List<BreedDomainEntity> = emptyList(),
        val errorMessage: String? = null
    )

    sealed class AllBreedsAction {
        object ErrorMessageShown : AllBreedsAction()
        data class BreedSelected(val breed: String) : AllBreedsAction()
    }

    companion object {
        private const val DEFAULT_ERROR_MESSAGE =
            "There has been a problem, please try again later."
    }
}
