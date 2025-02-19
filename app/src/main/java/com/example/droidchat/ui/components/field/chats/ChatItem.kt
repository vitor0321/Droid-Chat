package com.example.droidchat.ui.components.field.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.example.droidchat.domain.model.Chat
import com.example.droidchat.ui.mockPreview.ChatPreviewParameterProvider
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.image.TopazRoundedAvatar

@OptIn(ExperimentalTopazComposeLibraryApi::class)
@Composable
internal fun ChatItem(
    modifier: Modifier = Modifier,
    chat: Chat,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        val receiver = remember(chat.members) {
            chat.members.first { it.self.not() }
        }

        val (
            avatarRef,
            firstNameRef,
            _,
            lastMessageRef,
            lastMessageTimeRef,
            unreadCountRef) = createRefs()

        TopazRoundedAvatar(
            imageUri = receiver.profilePictureUrl,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(avatarRef) {
                    top.linkTo(parent.top, margin = DroidSpaceToken.MMedium.value)
                    bottom.linkTo(parent.bottom, margin = DroidSpaceToken.MMedium.value)
                    start.linkTo(parent.start)
                },
        )

        Text(
            text = receiver.firstName,
            modifier = Modifier.constrainAs(firstNameRef) {
                top.linkTo(avatarRef.top)
                start.linkTo(avatarRef.end, margin = DroidSpaceToken.MMedium.value)
                end.linkTo(lastMessageTimeRef.start, margin = DroidSpaceToken.MMedium.value)
                bottom.linkTo(lastMessageRef.top)
                width = Dimension.fillToConstraints
            },
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = Bold,
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = chat.lastMessage ?: "",
            modifier = Modifier.constrainAs(lastMessageRef) {
                top.linkTo(firstNameRef.bottom)
                start.linkTo(avatarRef.end, margin = DroidSpaceToken.MMedium.value)
                end.linkTo(unreadCountRef.start, margin = DroidSpaceToken.MMedium.value)
                bottom.linkTo(avatarRef.bottom)
                width = Dimension.fillToConstraints
            },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = chat.timestamp,
            modifier = Modifier.constrainAs(lastMessageTimeRef) {
                top.linkTo(firstNameRef.top)
                end.linkTo(parent.end)
                bottom.linkTo(firstNameRef.top)
                width = Dimension.wrapContent
            },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = chat.unreadCount.toString(),
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(DroidSpaceToken.MSmall.value)
                .constrainAs(unreadCountRef) {
                    top.linkTo(lastMessageTimeRef.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(lastMessageRef.bottom)
                    width = Dimension.wrapContent
                    visibility = if (chat.unreadCount > 0) Visibility.Visible
                    else Visibility.Gone
                },
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = Medium,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatItemPreview(
    @PreviewParameter(ChatPreviewParameterProvider::class)
    chat: Chat
) {
    DroidChatTheme {
        ChatItem(chat = chat)
    }
}