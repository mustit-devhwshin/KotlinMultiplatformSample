package com.example.kotlinmultiplatformsample.android

import android.app.Application
import androidx.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }
}

val module = module {
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
}