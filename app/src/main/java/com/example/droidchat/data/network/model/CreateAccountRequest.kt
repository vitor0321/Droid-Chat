package com.example.droidchat.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CreateAccountRequest(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePhoto: String?,
)