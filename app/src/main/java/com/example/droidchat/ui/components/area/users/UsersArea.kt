package com.example.droidchat.ui.components.area.users

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
import com.example.droidchat.ui.components.field.shared.error.GeneralError
import com.example.droidchat.ui.components.field.shared.list.GeneralEmptyList
import com.example.droidchat.ui.components.field.users.UsersErrorLoadMore
import com.example.droidchat.ui.components.field.users.UsersListItem
import com.example.droidchat.ui.components.field.users.UsersLoading
import com.example.droidchat.ui.components.field.users.UsersLoadingLoadMore
import com.example.droidchat.ui.mockPreview.userListPreviewParameterProvider
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.scaffold.TopazScaffold
import com.walcker.topaz.components.topAppBar.TopazTopAppBar
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTopazComposeLibraryApi::class)
@Composable
internal fun UsersArea(
    modifier: Modifier = Modifier,
    pagingUsers: LazyPagingItems<User>
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
                        contentPadding = PaddingValues(DroidSpaceToken.MMedium.value),
                        verticalArrangement = Arrangement.spacedBy(DroidSpaceToken.MMedium.value)
                    ) {
                        items(pagingUsers.itemCount) { index ->
                            UsersListItem(pagingUsers, index)
                        }

                        if (pagingUsers.loadState.append is LoadState.Loading)
                            item {
                                UsersLoadingLoadMore()
                            }

                        if (pagingUsers.loadState.append is LoadState.Error)
                            item {
                                UsersErrorLoadMore(
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
            pagingUsers = usersFlow.collectAsLazyPagingItems()
        )
    }
}