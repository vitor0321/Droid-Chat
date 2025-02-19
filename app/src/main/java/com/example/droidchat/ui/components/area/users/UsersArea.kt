package com.example.droidchat.ui.components.area.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.droidchat.domain.model.User
import com.example.droidchat.ui.components.field.users.UserItem
import com.example.droidchat.ui.mockPreview.userListPreviewParameterProvider
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken
import com.example.droidchat.ui.theme.Grey1
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
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize(),
            contentPadding = PaddingValues(DroidSpaceToken.MMedium.value),
            verticalArrangement = Arrangement.spacedBy(DroidSpaceToken.MMedium.value)
        ) {
            items(pagingUsers.itemCount) { index ->
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
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersAreaPreview() {
    DroidChatTheme {
        val usersFlow = flowOf(PagingData.from(userListPreviewParameterProvider))
        UsersArea(
            pagingUsers = usersFlow.collectAsLazyPagingItems()
        )
    }
}