package com.example.droidchat.ui.feature.chatDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.ChatMessage
import com.example.droidchat.ui.feature.chatDetails.components.area.ChatDetailsArea
import com.example.droidchat.ui.mockPreview.testData.pagingChatMessage
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun ChatDetailsRoute(
    onNavigationBack: () -> Unit,
) {

    ChatDetailsScreen(
        pagingChatMessage = pagingChatMessage.collectAsLazyPagingItems(),
        messageText = "",
        onNavigationBack = onNavigationBack,
        onMessageChanged = {},
        onSendClicked = {}
    )
}

@Composable
internal fun ChatDetailsScreen(
    pagingChatMessage: LazyPagingItems<ChatMessage>,
    messageText: String,
    onNavigationBack: () -> Unit,
    onMessageChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
) {
    ChatDetailsArea(
        pagingChatMessages = pagingChatMessage,
        messageText = messageText,
        onNavigationBack = onNavigationBack,
        onMessageChanged = onMessageChanged,
        onSendClicked = onSendClicked,
    )
}

@Preview(showBackground = true)
@Composable
private fun ChatDetails() {
    DroidChatTheme {
        ChatDetailsScreen(
            pagingChatMessage = pagingChatMessage.collectAsLazyPagingItems(),
            messageText = "",
            onNavigationBack = {},
            onMessageChanged = {},
            onSendClicked = {}
        )
    }
}