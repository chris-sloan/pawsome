package com.chrissloan.paw_some.di

import com.chrissloan.paw_some.data.utils.TimeUtils
import com.chrissloan.paw_some.utils.TimeUtilsImpl
import org.koin.dsl.module

object Dependencies {

    val utilsModule = module {
        single<TimeUtils> { TimeUtilsImpl() }
    }
}
