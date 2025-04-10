package com.example.droidchat.ui.components.field.users

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.User
import com.example.droidchat.ui.mockPreview.userListPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken
import com.example.droidchat.ui.theme.Grey1
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun UsersListItem(
    pagingUsers: LazyPagingItems<User>,
    index: Int,
) {
    val user = pagingUsers[index]
    user?.let {
        UserItem(user = it)

        if (index != pagingUsers.itemCount - 1) {
            HorizontalDivider(
                modifier = Modifier.padding(top = DroidSpaceToken.MMedium.value),
                color = Grey1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersListItemPreview() {
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
        UsersListItem(
            pagingUsers = usersFlow.collectAsLazyPagingItems(),
            index = 0,
        )
    }
}