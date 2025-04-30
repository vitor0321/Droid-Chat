package com.example.droidchat.data.model.response

import kotlinx.serialization.Serializable

@Serializable
internal data class PaginatedMessageResponse(
    val messages: List<MessageResponse>,
    val hasMore: Boolean,
    val total: Int,
)

@Serializable
internal data class MessageResponse(
    val id: Int,
    val isUnread: Boolean,
    val receiverId: Int,
    val senderId: Int,
    val text: String,
    val timestamp: Long,
)