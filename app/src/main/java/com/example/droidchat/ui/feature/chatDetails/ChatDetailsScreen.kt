package com.example.droidchat.ui.feature.chatDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.feature.chatDetails.components.area.ChatDetailsArea
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun ChatDetailsRoute(
    onNavigationBack: () -> Unit,
) {
    ChatDetailsScreen(
        messageText = "",
        onNavigationBack = onNavigationBack,
        onMessageChanged = {},
        onSendClicked = {}
    )
}

@Composable
internal fun ChatDetailsScreen(
    messageText: String,
    onNavigationBack: () -> Unit,
    onMessageChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
) {
    ChatDetailsArea(
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
            messageText = "",
            onNavigationBack = {},
            onMessageChanged = {},
            onSendClicked = {}
        )
    }
}