package com.example.droidchat.ui.components.area.chats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.droidchat.domain.model.Chat
import com.example.droidchat.ui.components.field.chats.ChatItemShimmer
import com.example.droidchat.ui.components.field.chats.ContentBoxChatField
import com.example.droidchat.ui.components.field.chats.SuccessField
import com.example.droidchat.ui.components.field.error.ErrorAnimated
import com.example.droidchat.ui.feature.chats.viewModel.ChatsListUiState
import com.example.droidchat.ui.preview.ChatListPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace
import com.example.droidchat.ui.theme.Grey1
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChatsArea(
    modifier: Modifier = Modifier,
    state: ChatsListUiState,
    onTryAgain: () -> Unit,
) {
    ContentBoxChatField(
        modifier = modifier,
    ) {
        when (state) {
            is ChatsListUiState.Loading ->
                Column(
                    modifier = Modifier.padding(DroidSpace.MMedium.value)
                ) {
                    repeat(8) { index ->
                        ChatItemShimmer()
                        if (index < 7)
                            HorizontalDivider(color = Grey1)
                    }
                }

            is ChatsListUiState.Success ->
                SuccessField(chats = state.chats)

            is ChatsListUiState.Error ->
                ErrorAnimated(onTryAgain = onTryAgain)
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ChatsAreaPreview(
    @PreviewParameter(ChatListPreviewParameterProvider::class)
    chats: ImmutableList<Chat>
) {
    DroidChatTheme {
        ChatsArea(
            state = ChatsListUiState.Success(chats),
            onTryAgain = {},
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun ChatsAreaLoadingPreview() {
    DroidChatTheme {
        ChatsArea(
            state = ChatsListUiState.Loading,
            onTryAgain = {},
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun ChatsAreaErrorPreview() {
    DroidChatTheme {
        ChatsArea(
            state = ChatsListUiState.Error,
            onTryAgain = {},
        )
    }
}