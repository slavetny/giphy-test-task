package com.example.giphy.di

import com.example.domain.usecase.GetGifUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetGifUseCase(get()) }
}