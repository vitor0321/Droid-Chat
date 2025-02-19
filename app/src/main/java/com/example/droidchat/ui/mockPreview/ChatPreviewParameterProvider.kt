package com.example.droidchat.ui.mockPreview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.droidchat.domain.model.Chat
import com.example.droidchat.ui.feature.chats.viewModel.ChatsListUiState
import com.example.droidchat.ui.mockPreview.ChatFake.chat1
import com.example.droidchat.ui.mockPreview.ChatFake.chat2
import com.example.droidchat.ui.mockPreview.ChatFake.chat3
import com.example.droidchat.ui.mockPreview.UserFake.user1
import com.example.droidchat.ui.mockPreview.UserFake.user2
import com.example.droidchat.ui.mockPreview.UserFake.user3
import com.example.droidchat.ui.mockPreview.UserFake.user4
import kotlinx.collections.immutable.persistentListOf

internal class ChatPreviewParameterProvider : PreviewParameterProvider<Chat> {

    override val values: Sequence<Chat> = sequenceOf(chat1, chat2, chat3)
}

internal class ChatListPreviewParameterProvider : PreviewParameterProvider<ChatsListUiState> {

    override val values: Sequence<ChatsListUiState> = sequenceOf(
        ChatsListUiState.Success(persistentListOf(chat2, chat3, chat1)),
        ChatsListUiState.Success(persistentListOf()),
        ChatsListUiState.Loading,
        ChatsListUiState.Error,
    )
}

internal object ChatFake {
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
}