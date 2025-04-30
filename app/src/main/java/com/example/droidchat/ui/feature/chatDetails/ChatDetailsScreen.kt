package com.example.droidchat.ui.feature.chatDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.ChatMessage
import com.example.droidchat.ui.feature.chatDetails.components.area.ChatDetailsArea
import com.example.droidchat.ui.feature.chatDetails.viewModel.ChatDetailsViewModel
import com.example.droidchat.ui.mockPreview.testData.pagingChatMessage
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun ChatDetailsRoute(
    viewModel: ChatDetailsViewModel = hiltViewModel(),
    onNavigationBack: () -> Unit,
) {

    ChatDetailsScreen(
        pagingChatMessage = viewModel.pagingChatMessages.collectAsLazyPagingItems(),
        messageText = viewModel.messageText,
        onNavigationBack = onNavigationBack,
        onMessageChanged = viewModel::onMessageChange,
        onSendClicked = viewModel::sendMessage
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