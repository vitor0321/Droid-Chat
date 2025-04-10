package com.example.droidchat.ui.components.area.chats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.droidchat.ui.components.field.chats.ChatItemShimmer
import com.example.droidchat.ui.components.field.chats.SuccessField
import com.example.droidchat.ui.components.field.shared.error.GeneralError
import com.example.droidchat.ui.components.field.shared.list.GeneralEmptyList
import com.example.droidchat.ui.feature.chats.viewModel.ChatsListUiState
import com.example.droidchat.ui.mockPreview.ChatListPreviewParameterProvider
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken
import com.example.droidchat.ui.theme.Grey1
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.scaffold.TopazScaffold
import com.walcker.topaz.components.topAppBar.TopazTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTopazComposeLibraryApi::class)
@Composable
internal fun ChatsArea(
    modifier: Modifier = Modifier,
    state: ChatsListUiState,
    onTryAgain: () -> Unit,
) {
    TopazScaffold(
        modifier = modifier,
        topBar = {
            TopazTopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            append(strings.chatsStrings.featureChatsGreetings)
                            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                            append("Vitor")
                        },
                    )
                }
            )
        },
    ) {
        when (state) {
            is ChatsListUiState.Loading ->
                Column(
                    modifier = Modifier.padding(DroidSpaceToken.MMedium.value)
                ) {
                    repeat(8) { index ->
                        ChatItemShimmer()
                        if (index < 7)
                            HorizontalDivider(color = Grey1)
                    }
                }

            is ChatsListUiState.Success -> {
                if (state.chats.isNotEmpty()) SuccessField(chats = state.chats)
                else GeneralEmptyList()
            }

            is ChatsListUiState.Error ->
                GeneralError(onClickButton = onTryAgain)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatsAreaPreview(
    @PreviewParameter(ChatListPreviewParameterProvider::class)
    state: ChatsListUiState
) {
    DroidChatTheme {
        ChatsArea(
            state = state,
            onTryAgain = {},
        )
    }
}