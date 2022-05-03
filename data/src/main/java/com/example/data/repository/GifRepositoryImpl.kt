package com.example.data.repository

import com.example.data.BuildConfig
import com.example.data.network.GiphyService
import com.example.domain.pojo.gif.Gif
import com.example.domain.repository.GifRepository

class GifRepositoryImpl(private val giphyService: GiphyService) : GifRepository {
    override suspend fun getGifList(request: String, limit: Int): Gif {
        return giphyService.getGifList(BuildConfig.apiKey, request, limit, "en")
    }
}