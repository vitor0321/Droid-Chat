package com.example.droidchat.ui.preview.mock

import com.example.droidchat.domain.model.User

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