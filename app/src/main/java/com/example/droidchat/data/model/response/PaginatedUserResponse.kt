package com.example.droidchat.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaginatedUserResponse(
    val users: List<UserResponse>,
    val hasMore: Boolean,
    val total: Int,
)

@Serializable
internal data class UserResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val profilePictureUrl: String?,
    @SerialName("userName")
    val email: String = "",
)