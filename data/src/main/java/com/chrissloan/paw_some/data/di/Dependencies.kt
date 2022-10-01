package com.chrissloan.paw_some.data.di

import com.chrissloan.paw_some.data.api.ApiFactory
import com.chrissloan.paw_some.data.api.CatApi
import com.chrissloan.paw_some.data.repository.BreedsRepositoryImpl
import com.chrissloan.paw_some.data.service.CatApiService
import com.chrissloan.paw_some.domain.repository.BreedsRepository
import org.koin.dsl.module

object Dependencies {
    val networkModule = module {

        single { ApiFactory() }
        single { (apiFactory:ApiFactory) -> apiFactory.create() }
        single { CatApiService(get()) }
        single<BreedsRepository> { BreedsRepositoryImpl(get()) }
    }
}
