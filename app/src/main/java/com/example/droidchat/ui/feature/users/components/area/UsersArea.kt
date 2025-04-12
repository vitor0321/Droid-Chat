package com.example.droidchat.ui.feature.users.components.area

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.User
import com.example.droidchat.ui.components.ErrorLazyColumnLoadMore
import com.example.droidchat.ui.components.LoadingLoadMore
import com.example.droidchat.ui.components.error.GeneralError
import com.example.droidchat.ui.components.list.GeneralEmptyList
import com.example.droidchat.ui.feature.users.components.field.UsersListItem
import com.example.droidchat.ui.feature.users.components.field.UsersLoading
import com.example.droidchat.ui.mockPreview.userListPreviewParameterProvider
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.scaffold.TopazScaffold
import com.walcker.topaz.components.topAppBar.TopazTopAppBar
import com.walcker.topaz.tokens.TopazSpacerSizeToken
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTopazComposeLibraryApi::class)
@Composable
internal fun UsersArea(
    modifier: Modifier = Modifier,
    pagingUsers: LazyPagingItems<User>,
    onUserClicked: (userId: Int) -> Unit,
) {
    TopazScaffold(
        modifier = modifier,
        topBar = {
            TopazTopAppBar(title = { Text(text = strings.usersStrings.featureUsersTitle) })
        }
    ) {
        when (pagingUsers.loadState.refresh) {
            LoadState.Loading -> UsersLoading()

            is LoadState.NotLoading ->
                if (pagingUsers.itemCount == 0)
                    GeneralEmptyList()
                else
                    LazyColumn(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .fillMaxSize(),
                        contentPadding = PaddingValues(TopazSpacerSizeToken.MMedium.all),
                        verticalArrangement = Arrangement.spacedBy(TopazSpacerSizeToken.MMedium.verticalWidth)
                    ) {
                        items(pagingUsers.itemCount) { index ->
                            UsersListItem(
                                pagingUsers = pagingUsers,
                                index = index,
                                onUserClicked = { userId -> onUserClicked(userId) }
                            )
                        }

                        if (pagingUsers.loadState.append is LoadState.Loading)
                            item {
                                LoadingLoadMore()
                            }

                        if (pagingUsers.loadState.append is LoadState.Error)
                            item {
                                ErrorLazyColumnLoadMore(
                                    description = strings.usersStrings.featureUsersErrorLoadingMore,
                                    onErrorLoadMore = { pagingUsers.retry() }
                                )
                            }
                    }

            is LoadState.Error ->
                GeneralError(onClickButton = { pagingUsers.refresh() })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersAreaPreview() {
    DroidChatTheme {
        val usersFlow = flowOf(
            PagingData.from(
                data = userListPreviewParameterProvider,
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Error(Throwable()),
                    prepend = LoadState.NotLoading(false),
                    append = LoadState.NotLoading(false),
                )
            )
        )
        UsersArea(
            pagingUsers = usersFlow.collectAsLazyPagingItems(),
            onUserClicked = { }
        )
    }
}