package com.example.droidchat.data.service

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.droidchat.data.database.DatabaseDataSource
import com.example.droidchat.data.database.DroidChatDatabase
import com.example.droidchat.data.database.entity.MessageEntity
import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.data.manager.SelfUserManager
import com.example.droidchat.data.mapper.ChatResponseMapper.toChatList
import com.example.droidchat.data.mapper.MessageEntityMapper.toDomain
import com.example.droidchat.data.model.request.PaginationParams
import com.example.droidchat.data.network.NetWorkDataSource
import com.example.droidchat.data.pagingSource.MessageRemoteMediator
import com.example.droidchat.data.util.safeCallResult
import com.example.droidchat.domain.ChatService
import com.example.droidchat.domain.model.Chat
import com.example.droidchat.domain.model.ChatMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.time.Instant
import javax.inject.Inject

internal class ChatServiceImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    private val databaseDataSource: DatabaseDataSource,
    private val database: DroidChatDatabase,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ChatService {

    override suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>> =
        safeCallResult(ioDispatcher) {
            val paginatedChatResponse = networkDataSource.getChats(
                paginationParams = PaginationParams(offset.toString(), limit.toString())
            )
            val selfUser = selfUserManager.selfUserFlow.firstOrNull()
            paginatedChatResponse.toChatList(selfUser?.id)
        }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPageMessages(receiverId: Int): Flow<PagingData<ChatMessage>> {
        val selfUser = runBlocking { selfUserManager.selfUserFlow.firstOrNull() }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = MessageRemoteMediator(
                receiverId = receiverId,
                netWorkDataSource = networkDataSource,
                databaseDataSource = databaseDataSource,
                database = database
            ),
            pagingSourceFactory = { databaseDataSource.getPagedMessage(receiverId = receiverId) }
        ).flow.map { pagingData ->
            pagingData.map { messageEntity ->
                messageEntity.toDomain(selfUserId = selfUser?.id)
            }
        }
    }

    override suspend fun sendMessage(receiverId: Int, message: String) {
        val selfUser = selfUserManager.selfUserFlow.firstOrNull()
        val messageEntity = MessageEntity(
            id = null,
            isUnread = false,
            senderId = selfUser?.id ?: 0,
            receiverId = receiverId,
            text = message,
            timestamp = Instant.now().toEpochMilli(),
        )

        databaseDataSource.insertMessage(listOf(messageEntity))
    }
}