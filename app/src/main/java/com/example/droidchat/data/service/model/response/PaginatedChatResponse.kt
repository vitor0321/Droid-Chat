package com.example.droidchat.data.service.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaginatedChatResponse(
    @SerialName("conversations")
    val chats: List<ChatResponse>,
    val hasMore: Boolean,
    val total: Int,
)