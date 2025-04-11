package com.example.droidchat.ui.feature.users.components.field

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@Composable
internal fun UsersLoadingLoadMore() {
    Box(
        modifier = Modifier
            .padding(TopazSpacerSizeToken.MMedium.horizontalHeight)
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