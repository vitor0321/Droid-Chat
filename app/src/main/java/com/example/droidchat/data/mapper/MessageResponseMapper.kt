package com.example.droidchat.data.mapper

import com.example.droidchat.data.database.entity.MessageEntity
import com.example.droidchat.data.model.response.PaginatedMessageResponse

internal object MessageResponseMapper {

    fun PaginatedMessageResponse.toEntityModel(): List<MessageEntity> =
        this.messages.map {
            MessageEntity(
                id = it.id,
                isUnread = it.isUnread,
                receiverId = it.receiverId,
                senderId = it.senderId,
                text = it.text,
                timestamp = it.timestamp
            )
        }
}