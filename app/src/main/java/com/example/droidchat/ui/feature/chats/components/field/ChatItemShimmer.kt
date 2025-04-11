package com.example.droidchat.ui.feature.chats.components.field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.droidchat.ui.theme.DroidChatTheme
import com.valentinilk.shimmer.shimmer
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@Composable
internal fun ChatItemShimmer() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .shimmer()
            .background(MaterialTheme.colorScheme.surface),
    ) {

        val (
            avatarRef,
            firstNameRef,
            _,
            lastMessageRef,
            lastMessageTimeRef,
            unreadCountRef) = createRefs()

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(TopazSpacerSizeToken.ExtraLarge.all)
                .background(Color.Gray)
                .constrainAs(avatarRef) {
                    top.linkTo(parent.top, margin = TopazSpacerSizeToken.MMedium.verticalWidth)
                    bottom.linkTo(parent.bottom, margin = TopazSpacerSizeToken.MMedium.verticalWidth)
                    start.linkTo(parent.start)
                },
        )

        Box(
            modifier = Modifier
                .height(TopazSpacerSizeToken.MMedium.verticalWidth)
                .background(Color.Gray)
                .constrainAs(firstNameRef) {
                    top.linkTo(avatarRef.top)
                    start.linkTo(avatarRef.end, margin = TopazSpacerSizeToken.MMedium.horizontalHeight)
                    end.linkTo(lastMessageTimeRef.start, margin = TopazSpacerSizeToken.MMedium.verticalWidth)
                    bottom.linkTo(lastMessageRef.top)
                    width = Dimension.fillToConstraints
                },
        )

        Box(
            modifier = Modifier
                .height(TopazSpacerSizeToken.MMedium.verticalWidth)
                .background(Color.Gray)
                .constrainAs(lastMessageRef) {
                    top.linkTo(firstNameRef.bottom)
                    start.linkTo(avatarRef.end, margin = TopazSpacerSizeToken.MMedium.verticalWidth)
                    end.linkTo(unreadCountRef.start, margin = TopazSpacerSizeToken.MMedium.verticalWidth)
                    bottom.linkTo(avatarRef.bottom)
                    width = Dimension.fillToConstraints
                },
        )

        Box(
            modifier = Modifier
                .size(
                    width = TopazSpacerSizeToken.MMedium.horizontalHeight,
                    height = TopazSpacerSizeToken.MMedium.verticalWidth
                )
                .background(Color.Gray)
                .constrainAs(lastMessageTimeRef) {
                    top.linkTo(firstNameRef.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(firstNameRef.top)
                    width = Dimension.wrapContent
                },
        )

        Box(
            modifier = Modifier
                .height(TopazSpacerSizeToken.MMedium.verticalWidth)
                .clip(CircleShape)
                .background(Color.Gray)
                .padding(TopazSpacerSizeToken.MSmall.all)
                .constrainAs(unreadCountRef) {
                    top.linkTo(lastMessageTimeRef.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(lastMessageRef.bottom)
                    width = Dimension.wrapContent
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatItemShimmerPreview() {
    DroidChatTheme {
        ChatItemShimmer()
    }
}