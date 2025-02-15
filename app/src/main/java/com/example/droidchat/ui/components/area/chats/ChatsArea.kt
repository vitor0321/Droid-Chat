package com.example.droidchat.ui.components.area.chats

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.droidchat.domain.model.Chat
import com.example.droidchat.ui.components.field.chats.ContentBoxChatField
import com.example.droidchat.ui.components.field.chats.SuccessField
import com.example.droidchat.ui.feature.chats.viewModel.ChatsListUiState
import com.example.droidchat.ui.preview.ChatListPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChatsArea(
    modifier: Modifier = Modifier,
    state: ChatsListUiState,
) {
    ContentBoxChatField(
        modifier = modifier,
    ) {
        when (state) {
            is ChatsListUiState.Loading -> {
                // Loading
            }

            is ChatsListUiState.Success -> {
                SuccessField(chats = state.chats)
            }

            is ChatsListUiState.Error -> {
                // Error
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatsAreaPreview(
    @PreviewParameter(ChatListPreviewParameterProvider::class)
    chats: ImmutableList<Chat>
) {
    DroidChatTheme {
        ChatsArea(
            state = ChatsListUiState.Success(chats)
        )
    }
}