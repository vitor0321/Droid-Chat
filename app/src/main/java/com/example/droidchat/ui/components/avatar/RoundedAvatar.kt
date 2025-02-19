package com.example.droidchat.ui.components.avatar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.droidchat.R
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace

@Composable
internal fun RoundedAvatar(
    modifier: Modifier = Modifier,
    imageUri: Any?,
    contentDescription: String?,
    size: DroidSpace = DroidSpace.ExtraLarge,
    @DrawableRes defaultImage: Int = R.drawable.no_profile_image,
) {
    AsyncImage(
        model = imageUri,
        contentDescription = contentDescription,
        modifier = modifier
            .clip(CircleShape)
            .size(size.value),
        placeholder = painterResource(defaultImage),
        error = painterResource(defaultImage),
        fallback = painterResource(defaultImage),
    )
}

@Preview(showBackground = true)
@Composable
private fun RoundedAvatarPreview() {
    DroidChatTheme {
        RoundedAvatar(
            imageUri = null,
            contentDescription = "Avatar",
        )
    }
}