package com.example.giphy.di

import com.example.data.repository.GifRepositoryImpl
import com.example.domain.repository.GifRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<GifRepository> { GifRepositoryImpl(get()) }
}