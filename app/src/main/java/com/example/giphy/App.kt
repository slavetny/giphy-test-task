package com.example.giphy

import android.app.Application
import com.example.giphy.di.networkModule
import com.example.giphy.di.repositoryModule
import com.example.giphy.di.useCaseModule
import com.example.giphy.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }
}