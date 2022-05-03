package com.example.giphy.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.pojo.GifData
import com.example.domain.usecase.GetGifUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class GifViewModel(private val getGifUseCase: GetGifUseCase) : ViewModel() {

    private val _gifListFlow = MutableSharedFlow<List<GifData>>()
    val gifListFlow: SharedFlow<List<GifData>> = _gifListFlow

    fun getGifList(request: String) = viewModelScope.launch {
        _gifListFlow.emit(getGifUseCase.invoke(request, 25).data)
    }
}