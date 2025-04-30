package com.example.droidchat.data.database

import androidx.paging.PagingSource
import com.example.droidchat.data.database.entity.MessageEntity
import com.example.droidchat.data.database.entity.MessageRemoteKeyEntity
import javax.inject.Inject

internal class DatabaseDataSourceImpl @Inject constructor(
    database: DroidChatDatabase,
) : DatabaseDataSource {

    private val messageDao = database.messageDao()
    private val messageRemoteKeyDao = database.messageRemoteKeyDao()

    override fun getPagedMessage(receiverId: Int): PagingSource<Int, MessageEntity> =
        messageDao.getPagedMessage(receiverId)

    override suspend fun insertMessage(messages: List<MessageEntity>) =
        messageDao.insertMessage(messages)

    override suspend fun clearMessage(receiverId: Int) =
        messageDao.clearMessage(receiverId)

    override suspend fun getMessageRemoteKey(receiverId: Int): MessageRemoteKeyEntity? =
        messageRemoteKeyDao.getRemoteKey(receiverId = receiverId)

    override suspend fun insertMessageRemoteKey(remoteKey: MessageRemoteKeyEntity) =
        messageRemoteKeyDao.insertRemoteKey(remoteKey = remoteKey)

    override suspend fun clearMessageRemoteKey(receiverId: Int) =
        messageRemoteKeyDao.clearRemoteKey(receiverId = receiverId)
}