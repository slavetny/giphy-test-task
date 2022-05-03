package com.example.giphy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.pojo.gif.GifData
import com.example.domain.usecase.GetGifUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class GifViewModel(private val getGifUseCase: GetGifUseCase) : ViewModel() {

    private val _gifListFlow = MutableSharedFlow<List<GifData>>()
    val gifListFlow: SharedFlow<List<GifData>> = _gifListFlow

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow: SharedFlow<String> = _errorFlow

    fun getGifList(request: String) = viewModelScope.launch {
        try {
            val response = getGifUseCase.invoke(request, 25)
            _gifListFlow.emit(response.data)
        } catch (e: HttpException) {
            _errorFlow.emit(e.localizedMessage ?: "Search error")
        }
    }
}