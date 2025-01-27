package com.example.droidchat.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaginatedChatResponse(
    @SerialName("conversation")
    val chats: List<ChatResponse>,
    val hasMore: Boolean,
    val total: Int,
)