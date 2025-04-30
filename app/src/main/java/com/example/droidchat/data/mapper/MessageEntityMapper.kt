package com.example.droidchat.data.mapper

import com.example.droidchat.data.database.entity.MessageEntity
import com.example.droidchat.domain.model.ChatMessage

internal object MessageEntityMapper {

    fun MessageEntity.toDomain(
        selfUserId: Int?,
    ): ChatMessage {
        return ChatMessage(
            autoId = autoId,
            id = id,
            senderId = senderId,
            receiverId = receiverId,
            text = text,
            formattedDateTime = timestamp.toTimestamp(),
            isUnread = isUnread,
            isSelf = senderId == selfUserId,
        )
    }
}