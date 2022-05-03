package com.example.giphy.di

import com.example.data.network.GiphyService
import com.example.data.network.NetworkHelper
import org.koin.dsl.module

val networkModule = module {
    single { NetworkHelper.provideRetrofit("https://api.giphy.com/") }
    single { NetworkHelper.provideService<GiphyService>(get()) }
}