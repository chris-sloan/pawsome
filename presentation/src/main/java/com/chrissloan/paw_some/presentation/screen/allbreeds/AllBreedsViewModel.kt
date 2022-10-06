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

    sealed class FilterProperty(
        open val type: FilterPropertyType = FilterPropertyType.Numerical,
        open val label: String
    ) {
        object Adaptability : FilterProperty(label = "Adaptability")
        object AffectionLevel : FilterProperty(label = "Affection Level")
        object ChildFriendly : FilterProperty(label = "Child Friendly")
        object DogFriendly : FilterProperty(label = "Dog Friendly")
        object EnergyLevel : FilterProperty(label = "Energy Level")
        object Experimental : FilterProperty(label = "Experimental")
        object Grooming : FilterProperty(label = "Grooming")
        object Hairless : FilterProperty(label = "Hairless")
        object HealthIssues : FilterProperty(label = "Health Issues")
        object Hypoallergenic : FilterProperty(label = "Hypoallergenic")
        object Indoor : FilterProperty(label = "Indoor")
        object Intelligence : FilterProperty(label = "Intelligence")
        object Natural : FilterProperty(label = "Natural")
        object SheddingLevel : FilterProperty(label = "Shedding Level")
        object ShortLegs : FilterProperty(label = "Short Legs")
        object SocialNeeds : FilterProperty(label = "Social Needs")
        object StrangerFriendly : FilterProperty(label = "Stranger Friendly")
        object SuppressedTail : FilterProperty(label = "Suppressed Tail")
        object Rare : FilterProperty(label = "Rare")
        object Rex : FilterProperty(label = "Rex")
        object Vocalisation : FilterProperty(label = "Vocalisation")

        sealed class FilterPropertyType {
            object Numerical : FilterPropertyType()
        }

        companion object {
            fun allProperties() = listOf(
                Adaptability, AffectionLevel, ChildFriendly, DogFriendly,
                EnergyLevel, Experimental, Grooming, Hairless, HealthIssues,
                Hypoallergenic, Indoor, Intelligence, Natural, SheddingLevel,
                ShortLegs, SocialNeeds, StrangerFriendly, SuppressedTail, Rare,
                Rex, Vocalisation
            )
        }
    }

    companion object {
        private val DEFAULT_ERROR_MESSAGE = R.string.error_default_message
        private val DEFAULT_FILTER_NOT_ACTIVE_MESSAGE = R.string.error_filter_not_active
    }
}
