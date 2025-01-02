package com.example.droidchat.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class TokenResponse(
    val token: String,
)