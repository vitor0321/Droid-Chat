package com.example.droidchat.ui.feature.chats

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.area.chats.ChatsArea
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun ChatsRoute() {
    ChatsScreenScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatsScreenScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Chats Walcker") })
        },
      content = { paddingValues ->
          ChatsArea(modifier = Modifier.padding(paddingValues))
      }
    )
}

@Preview(showBackground = true)
@Composable
private fun ChatsScreenPreview() {
    DroidChatTheme {
        ChatsScreenScreen()
    }
}