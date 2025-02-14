package com.example.droidchat.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AuthRequest(
    @SerialName("username")
    val email: String,
    val password: String,
)