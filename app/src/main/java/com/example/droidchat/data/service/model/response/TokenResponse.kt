package com.example.droidchat.data.service.model.response

import kotlinx.serialization.Serializable

@Serializable
internal data class TokenResponse(
    val token: String,
)