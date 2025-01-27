package com.example.droidchat.domain.model

import kotlinx.collections.immutable.ImmutableList

internal data class Chat(
    val id: Int,
    val lastMessage: String?,
    val members: ImmutableList<User>,
    val unreadCount: Int,
    val timestamp: String,
)
