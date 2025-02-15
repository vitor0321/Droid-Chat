package com.example.droidchat.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import com.example.droidchat.domain.model.Chat
import com.example.droidchat.ui.preview.mock.chat1
import com.example.droidchat.ui.preview.mock.chat2
import com.example.droidchat.ui.preview.mock.chat3
import kotlinx.collections.immutable.persistentListOf

internal class ChatPreviewParameterProvider : PreviewParameterProvider<Chat> {

    override val values: Sequence<Chat> = sequenceOf(chat1, chat2, chat3)
}

internal class ChatListPreviewParameterProvider : CollectionPreviewParameterProvider<Chat>(
    persistentListOf(
        chat1,
        chat2,
        chat3
    )
)