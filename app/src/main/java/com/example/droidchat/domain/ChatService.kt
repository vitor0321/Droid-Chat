package com.example.droidchat.domain

import androidx.paging.PagingData
import com.example.droidchat.domain.model.Chat
import com.example.droidchat.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

internal interface ChatService {

    suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>>

    fun getPageMessages(receiverId: Int): Flow<PagingData<ChatMessage>>

    suspend fun sendMessage(receiverId: Int, message: String)
}