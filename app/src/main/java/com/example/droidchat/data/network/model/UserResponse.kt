package com.example.droidchat.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val profilePictureUrl: String?,
    @SerialName("userName")
    val email: String,
)