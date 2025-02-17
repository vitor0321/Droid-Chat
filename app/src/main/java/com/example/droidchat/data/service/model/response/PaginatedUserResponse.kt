package com.example.droidchat.data.service.model.response

import kotlinx.serialization.Serializable

@Serializable
internal data class PaginatedUserResponse(
    val users: List<UserResponse>,
    val hasMore: Boolean,
    val total: Int,
)