package com.example.droidchat.ui.feature.users

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.User
import com.example.droidchat.ui.components.area.users.UsersArea
import com.example.droidchat.ui.mockPreview.userListPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
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
    UsersArea(pagingUsers = pagingUsers)
}

@Preview(showBackground = true)
@Composable
private fun UsersAreaPreview() {
    DroidChatTheme {
        val usersFlow = flowOf(PagingData.from(userListPreviewParameterProvider))
        UsersScreen(
            pagingUsers = usersFlow.collectAsLazyPagingItems()
        )
    }
}