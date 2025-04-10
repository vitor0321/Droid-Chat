package com.example.droidchat.ui.components.field.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken

@Composable
internal fun UsersLoadingLoadMore() {
    Box(
        modifier = Modifier
            .padding(DroidSpaceToken.MMedium.value)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersLoadingLoadMorePreview() {
    DroidChatTheme {
        UsersLoadingLoadMore()
    }
}