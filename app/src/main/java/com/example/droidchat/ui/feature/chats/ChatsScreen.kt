package com.example.droidchat.ui.feature.chats

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.ui.components.area.chats.ChatsArea
import com.example.droidchat.ui.strings.strings
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
            TopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            append(strings.chatsStrings.featureChatsGreetings)
                            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                            append("Vitor")
                        },
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                expandedHeight = 100.dp,
            )
        },
        content = { paddingValues ->
            ChatsArea(modifier = Modifier.padding(paddingValues))
        },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface,
    )
}

@Preview(showBackground = true)
@Composable
private fun ChatsScreenPreview() {
    DroidChatTheme {
        ChatsScreenScreen()
    }
}