package com.example.droidchat.ui.components.field.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.field.shared.PrimaryButton
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken

@Composable
internal fun UsersErrorLoadMore(
    onErrorLoadMore: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(DroidSpaceToken.MMedium.value)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(DroidSpaceToken.XSmall.value),
            text = strings.usersStrings.featureUsersErrorLoadingMore,
            color = MaterialTheme.colorScheme.error,
        )

        PrimaryButton(
            modifier = Modifier
                .padding(horizontal = DroidSpaceToken.MLarge.value)
                .height(DroidSpaceToken.XSLarge.value),
            text = strings.errorMessagesStrings.commonTryAgain,
            onClick = onErrorLoadMore,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersErrorLoadMorePreview() {
    DroidChatTheme {
        UsersErrorLoadMore(
            onErrorLoadMore = { }
        )
    }
}