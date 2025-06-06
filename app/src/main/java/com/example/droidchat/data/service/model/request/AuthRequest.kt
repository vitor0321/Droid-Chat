package com.example.droidchat.data.service.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AuthRequest(
    @SerialName("username")
    val email: String,
    val password: String,
)