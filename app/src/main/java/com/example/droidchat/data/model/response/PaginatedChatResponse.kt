package com.example.droidchat.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaginatedChatResponse(
    @SerialName("conversations")
    val chats: List<ChatResponse>,
    val hasMore: Boolean,
    val total: Int,
)

@Serializable
internal data class ChatResponse (
    val id: Int,
    val lastMessage: String?,
    val members: List<UserResponse>,
    val unreadCount: Int,
    val createdAt: Long,
    val updatedAt: Long,
)