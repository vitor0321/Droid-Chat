package com.example.droidchat.data.repository.mapper

import com.example.droidchat.data.network.model.UserResponse
import com.example.droidchat.domain.model.User

internal object UserMapper {

    fun UserResponse.toUser(selfUserIdi: Int?) = User(
        id = id,
        self = id == selfUserIdi,
        firstName = firstName,
        lastName = lastName,
        profilePictureUrl = profilePictureUrl,
        email = email
    )
}