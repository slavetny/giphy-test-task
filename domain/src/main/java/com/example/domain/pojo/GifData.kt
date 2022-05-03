package com.example.domain.pojo

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

data class Images(
    val downsized_large: DownsizedLarge,
    val downsized_medium: DownsizedMedium,
    val downsized_small: DownsizedSmall,
)

data class DownsizedLarge(
    val height: String,
    val size: String,
    val url: String,
    val width: String
)

data class DownsizedMedium(
    val height: String,
    val size: String,
    val url: String,
    val width: String
)

data class DownsizedSmall(
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val width: String
)