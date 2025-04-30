package com.example.droidchat.data.mapper

import com.example.droidchat.data.mapper.UserResponseMapper.toUser
import com.example.droidchat.data.model.response.PaginatedChatResponse
import com.example.droidchat.domain.model.Chat
import kotlinx.collections.immutable.toImmutableList

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