package com.example.droidchat.ui.components.area.chats

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.field.chats.ChatItem
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace
import com.example.droidchat.ui.theme.Grey1

@Composable
internal fun ChatsArea(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = DroidSpace.MMedium.value),
    ) {
        items(100) {
            ChatItem()
            HorizontalDivider(
                color = Grey1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatsAreaPreview() {
    DroidChatTheme {
        ChatsArea()
    }
}