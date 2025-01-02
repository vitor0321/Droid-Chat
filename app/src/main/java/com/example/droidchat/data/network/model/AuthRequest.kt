package com.example.droidchat.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class AuthRequest(
    val username: String,
    val password: String,
)