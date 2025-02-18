package com.example.droidchat.ui.mockPreview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import com.example.droidchat.domain.model.User
import com.example.droidchat.ui.mockPreview.UserFake.user2
import com.example.droidchat.ui.mockPreview.UserFake.user3
import com.example.droidchat.ui.mockPreview.UserFake.user4
import kotlinx.collections.immutable.persistentListOf

internal class UsersPreviewParameterProvider : PreviewParameterProvider<User> {

    override val values: Sequence<User> = sequenceOf(user2, user3, user4)
}

internal class UserListPreviewParameterProvider : CollectionPreviewParameterProvider<User>(
    persistentListOf(
        user2,
        user3,
        user4
    )
)

internal object UserFake {
    internal val user1 = User(
        id = 1,
        self = true,
        firstName = "John",
        lastName = "Doe",
        profilePictureUrl = "",
        email = "JohnDoe@gmail.com"
    )

    internal val user2 = User(
        id = 2,
        self = false,
        firstName = "Jane",
        lastName = "Doe",
        profilePictureUrl = "",
        email = "JaneDoe@gmail.com"
    )

    internal val user3 = User(
        id = 3,
        self = false,
        firstName = "Adriana",
        lastName = "Walcker",
        profilePictureUrl = "",
        email = "adrianawalcker@gmail.com"
    )

    internal val user4 = User(
        id = 4,
        self = false,
        firstName = "Vitor",
        lastName = "Walcker",
        profilePictureUrl = "",
        email = "vitorwalcker@gmail.com"
    )
}