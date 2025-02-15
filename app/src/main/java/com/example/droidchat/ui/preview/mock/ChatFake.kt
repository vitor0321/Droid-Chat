package com.example.droidchat.ui.preview.mock

import com.example.droidchat.domain.model.Chat
import kotlinx.collections.immutable.persistentListOf

internal val chat1 = Chat(
    id = 1,
    lastMessage = "Ok, vamos!",
    members = persistentListOf(user1, user2),
    unreadCount = 2,
    timestamp = "12:25"
)

internal val chat2 = Chat(
    id = 2,
    lastMessage = "Como vai?",
    members = persistentListOf(user1, user3),
    unreadCount = 0,
    timestamp = "15:00"
)


internal val chat3 = Chat(
    id = 3,
    lastMessage = "Oiiii",
    members = persistentListOf(user1, user4),
    unreadCount = 2,
    timestamp = "15:00"
)