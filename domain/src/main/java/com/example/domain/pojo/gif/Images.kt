package com.example.domain.pojo.gif

import com.example.domain.pojo.gif.downsized.DownsizedLarge
import com.example.domain.pojo.gif.downsized.DownsizedMedium
import com.example.domain.pojo.gif.downsized.DownsizedSmall

data class Images(
    val downsized_large: DownsizedLarge,
    val downsized_medium: DownsizedMedium,
    val downsized_small: DownsizedSmall,
)