package com.example.droidchat.data.service.model.response

import kotlinx.serialization.Serializable

@Serializable
internal data class ChatResponse (
    val id: Int,
    val lastMessage: String?,
    val members: List<UserResponse>,
    val unreadCount: Int,
    val createdAt: Long,
    val updatedAt: Long,
)