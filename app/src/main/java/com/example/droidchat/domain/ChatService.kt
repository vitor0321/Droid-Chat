package com.example.droidchat.domain

import com.example.droidchat.domain.model.Chat

internal interface ChatService {

    suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>>
}