package com.example.droidchat.ui.mockPreview.testData

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.example.droidchat.domain.model.ChatMessage
import kotlinx.coroutines.flow.flowOf

internal val chatMessage1TestData = ChatMessage(
    autoId = 1,
    id = 1,
    senderId = 1,
    receiverId = 2,
    text = "Olá",
    formattedDateTime = "15:00",
    isUnread = true,
    isSelf = true
)

internal val chatMessage2TestData = ChatMessage(
    autoId = 2,
    id = 2,
    senderId = 2,
    receiverId = 1,
    text = "Oi, tudo bem?",
    formattedDateTime = "15:01",
    isUnread = false,
    isSelf = false
)

internal val chatMessage3TestData = ChatMessage(
    autoId = 3,
    id = 3,
    senderId = 1,
    receiverId = 2,
    text = "Sim, e com você? Que bom te ver por aqui!",
    formattedDateTime = "15:03",
    isUnread = false,
    isSelf = false
)

internal val chatMessage4TestData = ChatMessage(
    autoId = 4,
    id = 4,
    senderId = 2,
    receiverId = 1,
    text = "Estou bem também! Vamos marcar alguma coisa para o fim de semana?",
    formattedDateTime = "15:05",
    isUnread = true,
    isSelf = true
)

internal val chatMessage5TestData = ChatMessage(
    autoId = 5,
    id = 5,
    senderId = 1,
    receiverId = 2,
    text = "Claro! Podemos ir ao cinema, o que acha?",
    formattedDateTime = "15:10",
    isUnread = false,
    isSelf = true
)

internal val pagingChatMessage = flowOf(
    PagingData.from(
        listOf(
            chatMessage5TestData,
            chatMessage4TestData,
            chatMessage3TestData,
            chatMessage2TestData,
            chatMessage1TestData,
        ),
        sourceLoadStates = LoadStates(
            refresh = LoadState.NotLoading(endOfPaginationReached = true),
            prepend = LoadState.NotLoading(endOfPaginationReached = true),
            append = LoadState.NotLoading(endOfPaginationReached = true)
        )
    )
)