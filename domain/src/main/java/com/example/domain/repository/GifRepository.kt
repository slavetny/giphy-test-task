package com.example.domain.repository

import com.example.domain.pojo.Gif

interface GifRepository {
    suspend fun getGifList(request: String, limit: Int): Gif
}