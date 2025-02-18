package com.example.droidchat.ui.components.field.chats

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.droidchat.domain.model.Chat
import com.example.droidchat.ui.mockPreview.ChatListPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace
import com.example.droidchat.ui.theme.Grey1
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun SuccessField(
    chats: ImmutableList<Chat>,
) {
    val lazyListState: LazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = DroidSpace.MMedium.value),
    ) {
        itemsIndexed(
            items = chats,
            key = { index, chat -> chat.id }
        ) { _, chat ->

            ChatItem(chat = chat)

            if (chat != chats.last())
                HorizontalDivider(color = Grey1)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccessAreaPreview(
    @PreviewParameter(ChatListPreviewParameterProvider::class)
    chats: ImmutableList<Chat>
) {
    DroidChatTheme {
        SuccessField(
            chats = chats
        )
    }
}