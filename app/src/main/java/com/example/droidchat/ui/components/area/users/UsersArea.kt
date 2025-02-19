package com.example.droidchat.ui.components.area.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.User
import com.example.droidchat.ui.components.field.users.UserItem
import com.example.droidchat.ui.feature.users.UsersScreen
import com.example.droidchat.ui.mockPreview.userListPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace
import com.example.droidchat.ui.theme.Grey1
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun UsersArea(
    pagingUsers: LazyPagingItems<User>
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(DroidSpace.MMedium.value),
            verticalArrangement = Arrangement.spacedBy(DroidSpace.MMedium.value)
        ) {
            items(pagingUsers.itemCount) { index ->
                val user = pagingUsers[index]
                user?.let {
                    UserItem(user = it)

                    if (index != pagingUsers.itemCount - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(top = DroidSpace.MMedium.value),
                            color = Grey1)
                    }
                }
            }
        }
    }
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