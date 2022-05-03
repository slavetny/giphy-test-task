package com.example.domain.usecase

import com.example.domain.pojo.Gif
import com.example.domain.repository.GifRepository

class GetGifUseCase(private val gifRepository: GifRepository) {
    suspend operator fun invoke(request: String, limit: Int): Gif {
        return gifRepository.getGifList(request, limit)
    }
}