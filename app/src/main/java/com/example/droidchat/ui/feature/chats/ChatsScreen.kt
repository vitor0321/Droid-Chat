package com.example.droidchat.ui.feature.chats

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.droidchat.ui.components.area.chats.ChatsArea
import com.example.droidchat.ui.components.field.chats.TopAppBarField
import com.example.droidchat.ui.feature.chats.viewModel.ChatsListUiState
import com.example.droidchat.ui.feature.chats.viewModel.ChatsViewModel
import com.example.droidchat.ui.theme.DroidChatTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ChatsRoute(
    viewModel: ChatsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    ChatsScreenScreen(
        state = state.value,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatsScreenScreen(
    state: ChatsListUiState,
) {
    Scaffold(
        topBar = { TopAppBarField() },
        content = { paddingValues ->
            ChatsArea(
                modifier = Modifier.padding(paddingValues),
                state = state
            )
        },
        containerColor = MaterialTheme.colorScheme.primary,
    )
}

@Preview(showBackground = true)
@Composable
private fun ChatsScreenLoadingPreview() {
    DroidChatTheme {
        ChatsScreenScreen(
            state = ChatsListUiState.Loading,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatsScreenSuccessPreview() {
    DroidChatTheme {
        ChatsScreenScreen(
            state = ChatsListUiState.Success(persistentListOf()),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatsScreenErrorPreview() {
    DroidChatTheme {
        ChatsScreenScreen(
            state = ChatsListUiState.Error,
        )
    }
}