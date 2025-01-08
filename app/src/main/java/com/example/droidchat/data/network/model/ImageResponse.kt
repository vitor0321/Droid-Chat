package com.example.droidchat.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ImageResponse(
    val id: Int,
    val name: String,
    val type: String,
    val url: String,
)