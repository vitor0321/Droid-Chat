package com.example.droidchat.data.service

import com.example.droidchat.data.NetWorkDataSource
import com.example.droidchat.data.SelfUserManager
import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.data.service.mapper.ChatResponseMapper.toChatList
import com.example.droidchat.data.service.model.request.PaginationParams
import com.example.droidchat.domain.ChatService
import com.example.droidchat.domain.model.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ChatServiceImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ChatService {

    override suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>> =
        withContext(ioDispatcher) {
            runCatching {
                val paginatedChatResponse = networkDataSource.getChats(
                    paginationParams = PaginationParams(offset.toString(), limit.toString())
                )

                val selfUser = selfUserManager.selfUserFlow.firstOrNull()
                paginatedChatResponse.toChatList(selfUser?.id)
            }
        }
}