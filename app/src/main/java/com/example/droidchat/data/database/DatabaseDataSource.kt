package com.example.droidchat.data.database

import androidx.paging.PagingSource
import com.example.droidchat.data.database.entity.MessageEntity
import com.example.droidchat.data.database.entity.MessageRemoteKeyEntity

internal interface DatabaseDataSource {

    fun getPagedMessage(receiverId: Int): PagingSource<Int, MessageEntity>

    suspend fun insertMessage(messages: List<MessageEntity>)

    suspend fun clearMessage(receiverId: Int)

    suspend fun getMessageRemoteKey(receiverId: Int): MessageRemoteKeyEntity?

    suspend fun insertMessageRemoteKey(remoteKey: MessageRemoteKeyEntity)

    suspend fun clearMessageRemoteKey(receiverId: Int)
}