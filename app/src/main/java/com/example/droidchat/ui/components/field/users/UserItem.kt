package com.example.droidchat.ui.components.field.users

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.droidchat.domain.model.User
import com.example.droidchat.ui.mockPreview.UsersPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.image.TopazRoundedAvatar
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@OptIn(ExperimentalTopazComposeLibraryApi::class)
@Composable
internal fun UserItem(
    modifier: Modifier = Modifier,
    user: User
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopazRoundedAvatar(
            imageUri = user.profilePictureUrl,
            contentDescription = null,
            size = TopazSpacerSizeToken.XSLarge
        )

        Text(
            text = user.firstName,
            modifier = Modifier
                .padding(horizontal = DroidSpaceToken.MMedium.value)
                .weight(1f),
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserItemPreview(
    @PreviewParameter(UsersPreviewParameterProvider::class)
    user: User
) {
    DroidChatTheme {
        UserItem(
            user = user
        )
    }
}