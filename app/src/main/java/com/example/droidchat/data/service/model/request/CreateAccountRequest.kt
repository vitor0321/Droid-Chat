package com.example.droidchat.data.service.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CreateAccountRequest(
    val firstName: String,
    val lastName: String,
    @SerialName("username")
    val email: String,
    val password: String,
    val profilePictureId: Int?,
)