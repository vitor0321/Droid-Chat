package com.example.droidchat.ui.components.field.chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.droidchat.R
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace

@Composable
internal fun ChatItem(
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        val (
            avatarRef,
            firstNameRef,
            _,
            lastMessageRef,
            lastMessageTimeRef,
            unreadCountRef) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.no_profile_image),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(DroidSpace.ExtraLarge.value)
                .constrainAs(avatarRef) {
                    top.linkTo(parent.top, margin = DroidSpace.MMedium.value)
                    bottom.linkTo(parent.bottom, margin = DroidSpace.MMedium.value)
                    start.linkTo(parent.start)
                },
        )

        Text(
            text = "John",
            modifier = Modifier.constrainAs(firstNameRef) {
                top.linkTo(avatarRef.top)
                start.linkTo(avatarRef.end, margin = DroidSpace.MMedium.value)
                end.linkTo(lastMessageTimeRef.start, margin = DroidSpace.MMedium.value)
                bottom.linkTo(lastMessageRef.top)
                width = Dimension.fillToConstraints
            },
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = Bold,
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = "Ok, vamos!",
            modifier = Modifier.constrainAs(lastMessageRef) {
                top.linkTo(firstNameRef.bottom)
                start.linkTo(avatarRef.end, margin = DroidSpace.MMedium.value)
                end.linkTo(unreadCountRef.start, margin = DroidSpace.MMedium.value)
                bottom.linkTo(avatarRef.bottom)
                width = Dimension.fillToConstraints
            },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "12:25",
            modifier = Modifier.constrainAs(lastMessageTimeRef) {
                top.linkTo(firstNameRef.top)
                end.linkTo(parent.end)
                bottom.linkTo(unreadCountRef.top)
                width = Dimension.wrapContent
            },
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "2",
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(DroidSpace.MSmall.value)
                .constrainAs(unreadCountRef) {
                    top.linkTo(lastMessageTimeRef.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(lastMessageRef.bottom)
                    width = Dimension.wrapContent
                },
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = Medium,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatItemPreview() {
    DroidChatTheme {
        ChatItem()
    }
}