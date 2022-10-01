package com.chrissloan.paw_some

import android.app.Application
import com.chrissloan.paw_some.data.di.Dependencies.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PawsomeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PawsomeApplication)
            modules(
                listOf(
                    networkModule
                )
            )
        }
    }
}
