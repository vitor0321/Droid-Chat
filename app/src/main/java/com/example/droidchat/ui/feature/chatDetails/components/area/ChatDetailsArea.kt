package com.example.droidchat.ui.feature.chatDetails.components.area

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.ChatMessage
import com.example.droidchat.ui.components.ChatMessageBubble
import com.example.droidchat.ui.components.ChatMessageTextField
import com.example.droidchat.ui.components.ErrorLazyColumnLoadMore
import com.example.droidchat.ui.components.LoadingLoadMore
import com.example.droidchat.ui.components.error.GeneralError
import com.example.droidchat.ui.components.list.GeneralEmptyList
import com.example.droidchat.ui.feature.chatDetails.components.field.ChatDetailsTopAppBar
import com.example.droidchat.ui.mockPreview.testData.pagingChatMessage
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.scaffold.TopazScaffold
import com.walcker.topaz.components.topAppBar.TopazTopAppBar
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@OptIn(ExperimentalTopazComposeLibraryApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun ChatDetailsArea(
    pagingChatMessages: LazyPagingItems<ChatMessage>,
    messageText: String,
    onNavigationBack: () -> Unit,
    onMessageChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
) {
    TopazScaffold(
        topBar = {
            TopazTopAppBar(
                title = {
                    ChatDetailsTopAppBar()
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            onNavigationBack()
                        },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            )
        },
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (pagingChatMessages.loadState.refresh) {
                    is LoadState.Loading ->
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                    is LoadState.NotLoading -> {
                        if (pagingChatMessages.itemCount == 0)
                            GeneralEmptyList()
                        else
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentPadding = PaddingValues(TopazSpacerSizeToken.MMedium.all),
                                reverseLayout = true,
                                verticalArrangement = Arrangement.spacedBy(TopazSpacerSizeToken.MMedium.verticalWidth, Alignment.Bottom)
                            ) {
                                items(pagingChatMessages.itemCount) { index ->
                                    val chatMessage = pagingChatMessages[index]
                                    val previousChatMessage = if (index > 0) {
                                        pagingChatMessages[index - 1]
                                    } else null

                                    if (chatMessage != null)
                                        ChatMessageBubble(
                                            chatMessage = chatMessage,
                                            previousChatMessage = previousChatMessage
                                        )
                                }

                                if (pagingChatMessages.loadState.append is LoadState.Loading)
                                    item {
                                        LoadingLoadMore()
                                    }

                                if (pagingChatMessages.loadState.append is LoadState.Error)
                                    item {
                                        ErrorLazyColumnLoadMore(
                                            description = strings.chatDetailsStrings.featureChatDetailErrorLoadingMore,
                                            onErrorLoadMore = { pagingChatMessages.retry() }
                                        )
                                    }
                            }
                    }

                    is LoadState.Error ->
                        GeneralError()
                }

            }
            ChatMessageTextField(
                modifier = Modifier
                    .padding(horizontal = TopazSpacerSizeToken.MMedium.all)
                    .padding(
                        bottom = TopazSpacerSizeToken.MMedium.all,
                        top = TopazSpacerSizeToken.XSmall.all
                    ),
                value = messageText,
                placeholder = strings.chatDetailsStrings.featureChatDetailTextFieldPlaceholder,
                onSendClicked = onSendClicked,
                onInputChange = onMessageChanged,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatDetailsAreaPreview() {
    DroidChatTheme {
        ChatDetailsArea(
            pagingChatMessages = pagingChatMessage.collectAsLazyPagingItems(),
            messageText = "",
            onNavigationBack = { },
            onMessageChanged = { },
            onSendClicked = { },
        )
    }
}