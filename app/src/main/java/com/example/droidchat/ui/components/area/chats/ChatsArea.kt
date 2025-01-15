package com.example.droidchat.ui.components.area.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.ui.components.field.chats.ChatItem
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace
import com.example.droidchat.ui.theme.Grey1

@Composable
internal fun ChatsArea(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp),
                )
            )
            .clip(
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp),
                )
            )
            .fillMaxSize(),
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