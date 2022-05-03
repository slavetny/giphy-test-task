package com.example.domain.pojo.gif

data class GifData(
    val rating: String,
    val source: String,
    val title: String,
    val trending_datetime: String,
    val type: String,
    val url: String,
    val username: String,
    val images: Images,
)