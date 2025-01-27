package com.example.droidchat.ui.feature.chats.viewModel

import com.example.droidchat.domain.model.Chat
import kotlinx.collections.immutable.ImmutableList

internal sealed interface ChatsListUiState {
    data object Loading : ChatsListUiState
    class Success(val chats: ImmutableList<Chat>) : ChatsListUiState
    data object Error : ChatsListUiState
}