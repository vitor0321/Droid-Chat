package com.example.droidchat.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.domain.model.ChatMessage
import com.example.droidchat.ui.mockPreview.testData.chatMessage1TestData
import com.example.droidchat.ui.mockPreview.testData.chatMessage2TestData
import com.example.droidchat.ui.mockPreview.testData.chatMessage3TestData
import com.example.droidchat.ui.mockPreview.testData.chatMessage4TestData
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@Composable
internal fun ChatMessageBubble(
    chatMessage: ChatMessage,
    previousChatMessage: ChatMessage?,
    modifier: Modifier = Modifier
) {
    val isCurrentUserMessage = chatMessage.isSelf
    val isSameUserAsPrevious = previousChatMessage?.senderId == chatMessage.senderId

    val surfaceColor = if (isCurrentUserMessage) MaterialTheme.colorScheme.tertiary
    else MaterialTheme.colorScheme.secondary

    val horizontalAlignment = if (isCurrentUserMessage) Alignment.End
    else Alignment.Start

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = horizontalAlignment
    ) {
        Surface(
            modifier = Modifier.wrapContentWidth(),
            shape = RoundedCornerShape(TopazSpacerSizeToken.XLExtraLarge.horizontalHeight),
            color = surfaceColor,
        ) {
            Text(
                text = chatMessage.text,
                modifier = Modifier
                    .padding(
                        horizontal = TopazSpacerSizeToken.XMedium.horizontalHeight,
                        vertical = TopazSpacerSizeToken.XSmall.horizontalHeight
                    ),
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        if (isSameUserAsPrevious.not()) {
            Text(
                text = chatMessage.formattedDateTime,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatBubblePreview() {
    DroidChatTheme {
        ChatMessageBubble(
            chatMessage = chatMessage1TestData,
            previousChatMessage = chatMessage2TestData
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatBubbleSenderPreview() {
    DroidChatTheme {
        ChatMessageBubble(
            chatMessage = chatMessage3TestData,
            previousChatMessage = chatMessage4TestData
        )
    }
}