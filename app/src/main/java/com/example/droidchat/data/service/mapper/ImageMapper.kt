package com.example.droidchat.data.service.mapper

import com.example.droidchat.data.service.model.response.ImageResponse
import com.example.droidchat.domain.model.Image

internal object ImageMapper {

    fun ImageResponse.toImage() = Image(
        id = id,
        name = name,
        type = type,
        url = url
    )
}