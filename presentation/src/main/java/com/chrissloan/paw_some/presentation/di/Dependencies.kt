package com.chrissloan.paw_some.presentation.di

import com.chrissloan.paw_some.domain.usecase.BreedDetailUseCase
import com.chrissloan.paw_some.domain.usecase.BreedImagesUseCase
import com.chrissloan.paw_some.domain.usecase.FetchBreedsUseCase
import com.chrissloan.paw_some.presentation.screen.allbreeds.AllBreedsViewModel
import com.chrissloan.paw_some.presentation.screen.breeddetail.BreedDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object Dependencies {

    val useCaseModule = module {
        single { FetchBreedsUseCase(get()) }
        single { BreedDetailUseCase(get()) }
        single { BreedImagesUseCase(get()) }
    }

    val presentationModule = module {
        viewModel { AllBreedsViewModel(get()) }
        viewModel { (breedId: String) -> BreedDetailViewModel(breedId, get(), get()) }
    }

}
