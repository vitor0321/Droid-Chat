package com.example.droidchat.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CreateAccountRequest(
    val firstName: String,
    val lastName: String,
    val password: String,
    val profilePictureId: Int?,
    val username: String,
)