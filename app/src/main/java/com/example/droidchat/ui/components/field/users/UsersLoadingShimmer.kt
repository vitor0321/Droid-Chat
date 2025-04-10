package com.example.droidchat.ui.components.field.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.field.users.UsersLoading.repeatsShimmer
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken
import com.example.droidchat.ui.theme.Grey1

@Composable
internal fun UsersLoading() {
    Column(
        modifier = Modifier
            .padding(DroidSpaceToken.MMedium.value)
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(DroidSpaceToken.MMedium.value)
    ) {
        repeat(repeatsShimmer) { index ->
            UserItemShimmer()
            if (index < repeatsShimmer - 1)
                HorizontalDivider(color = Grey1)
        }
    }
}

private object UsersLoading {
    const val repeatsShimmer = 12
}

@Preview(showBackground = true)
@Composable
private fun UsersLoadingPreview() {
    DroidChatTheme {
        UsersLoading()
    }
}