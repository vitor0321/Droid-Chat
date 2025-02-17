package com.example.droidchat.data.repository

import com.example.droidchat.data.NetWorkDataSource
import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.data.manager.selfuser.SelfUserManager
import com.example.droidchat.data.network.model.PaginationParams
import com.example.droidchat.data.repository.mapper.ChatResponseMapper.toChatList
import com.example.droidchat.domain.ChatRepository
import com.example.droidchat.domain.model.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ChatRepositoryImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ChatRepository {

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