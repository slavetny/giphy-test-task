package com.example.data.network

import com.example.domain.pojo.Gif
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {
    @GET("v1/gifs/search")
    suspend fun getGifList(
        @Query("api_key") apiKey: String,
        @Query("q") request: String,
        @Query("limit") limit: Int,
        @Query("lang") language: String
    ) : Gif
}