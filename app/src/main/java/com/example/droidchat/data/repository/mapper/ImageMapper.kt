package com.example.droidchat.data.repository.mapper

import com.example.droidchat.data.network.model.ImageResponse
import com.example.droidchat.domain.model.Image

internal object ImageMapper {

    fun ImageResponse.toImage() = Image(
        id = id,
        name = name,
        type = type,
        url = url
    )
}