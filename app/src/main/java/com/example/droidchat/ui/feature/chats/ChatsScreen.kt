package com.example.droidchat.ui.feature.chats

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.droidchat.ui.components.area.chats.ChatsArea
import com.example.droidchat.ui.components.field.chats.TopAppBarField
import com.example.droidchat.ui.feature.chats.viewModel.ChatsListUiState
import com.example.droidchat.ui.feature.chats.viewModel.ChatsViewModel
import com.example.droidchat.ui.mockPreview.ChatListPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun ChatsRoute(
    viewModel: ChatsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onTryAgain = remember(viewModel) { { viewModel.getChats() } }

    ChatsScreenScreen(
        state = state.value,
        onTryAgain = viewModel::getChats,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatsScreenScreen(
    state: ChatsListUiState,
    onTryAgain: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBarField() },
        content = { paddingValues ->
            ChatsArea(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                state = state,
                onTryAgain = onTryAgain,
            )
        },
        containerColor = MaterialTheme.colorScheme.primary,
    )
}

@Preview(showBackground = true)
@Composable
private fun ChatsAreaSuccessPreview(
    @PreviewParameter(ChatListPreviewParameterProvider::class) state: ChatsListUiState
) {
    DroidChatTheme {
        ChatsArea(
            state = state,
            onTryAgain = {},
        )
    }
}