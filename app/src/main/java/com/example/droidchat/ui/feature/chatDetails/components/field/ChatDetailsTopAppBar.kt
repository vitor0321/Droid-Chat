package com.example.droidchat.ui.feature.chatDetails.components.field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.image.TopazRoundedAvatar
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@Composable
@OptIn(ExperimentalTopazComposeLibraryApi::class)
internal fun ChatDetailsTopAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TopazRoundedAvatar(
            imageUri = null,
            contentDescription = "User Avatar",
            size = TopazSpacerSizeToken.XSLarge,
            modifier = Modifier
        )
        Column(
            modifier = Modifier.padding(TopazSpacerSizeToken.MMedium.verticalWidth),
        ) {
            Text(
                text = "John Doe",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Online",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatDetailsTopAppBarPreview() {
    DroidChatTheme {
        ChatDetailsTopAppBar()
    }
}