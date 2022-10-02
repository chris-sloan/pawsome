package com.chrissloan.paw_some.presentation.di

import com.chrissloan.paw_some.domain.usecase.FetchBreedsUseCase
import com.chrissloan.paw_some.presentation.screen.AllBreedsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object Dependencies {

    val useCaseModule = module {
        single { FetchBreedsUseCase(get()) }
    }

    val presentationModule = module {
        viewModel { AllBreedsViewModel(get()) }
    }

}
