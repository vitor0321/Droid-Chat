package com.example.droidchat.ui.feature.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.User
import com.example.droidchat.ui.mockPreview.UserListPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun UsersRoute(
    viewModel: UsersViewModel = hiltViewModel()
) {
    val pagingUsers = viewModel.usersFlow.collectAsLazyPagingItems()
    UsersScreen(
        pagingUsers = pagingUsers,
    )
}

@Composable
internal fun UsersScreen(
    pagingUsers: LazyPagingItems<User>
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(DroidSpace.MMedium.value),
            verticalArrangement = Arrangement.spacedBy(DroidSpace.XSmall.value)
        ) {
            items(pagingUsers.itemCount) { index ->
                val user = pagingUsers[index]
                user?.let {
                    Text(text = it.firstName)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersPreview(
    @PreviewParameter(UserListPreviewParameterProvider::class) users: ImmutableList<User>
) {
    DroidChatTheme {
        val usersFlow = flowOf(PagingData.from(users))
        UsersScreen(
            pagingUsers = usersFlow.collectAsLazyPagingItems()
        )
    }
}