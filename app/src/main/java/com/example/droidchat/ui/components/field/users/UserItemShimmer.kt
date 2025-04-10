package com.example.droidchat.ui.components.field.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken
import com.valentinilk.shimmer.shimmer
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.image.TopazRoundedAvatar
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@OptIn(ExperimentalTopazComposeLibraryApi::class)
@Composable
internal fun UserItemShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shimmer()
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TopazRoundedAvatar(
            imageUri = null,
            contentDescription = null,
            size = TopazSpacerSizeToken.XSLarge
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DroidSpaceToken.MMedium.value)
                .weight(1f)
                .height(DroidSpaceToken.MMedium.value)
                .background(Color.Gray),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserItemShimmerPreview() {
    DroidChatTheme {
        UserItemShimmer()
    }
}