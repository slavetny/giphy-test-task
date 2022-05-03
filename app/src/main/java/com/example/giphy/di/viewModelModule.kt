package com.example.giphy.di

import com.example.giphy.presentation.search.GifViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GifViewModel(get()) }
}