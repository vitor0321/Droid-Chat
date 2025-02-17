package com.example.droidchat.data.service.mapper

import com.example.droidchat.data.network.model.response.PaginatedChatResponse
import com.example.droidchat.data.service.mapper.UserMapper.toUser
import com.example.droidchat.domain.model.Chat
import kotlinx.collections.immutable.toImmutableList
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

internal object ChatResponseMapper {

    fun PaginatedChatResponse.toChatList(selfUserIdi: Int?): List<Chat> = this.chats.map { chatResponse ->
        Chat(
            id = chatResponse.id,
            lastMessage = chatResponse.lastMessage,
            members = chatResponse.members.map { userResponse ->
                userResponse.toUser(selfUserIdi = selfUserIdi)
            }.toImmutableList(),
            unreadCount = chatResponse.unreadCount,
            timestamp = chatResponse.updatedAt.toTimestamp(),
        )
    }
}

private fun Long.toTimestamp(): String {
    val messageDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(this),
        ZoneId.systemDefault()
    )

    val now = LocalDateTime.now()
    return if (messageDateTime.toLocalDate() == now.toLocalDate()) {
        messageDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    } else {
        messageDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}