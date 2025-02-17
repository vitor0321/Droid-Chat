package com.example.droidchat.data.service.mapper

import com.example.droidchat.data.service.model.response.PaginatedUserResponse
import com.example.droidchat.domain.model.User

internal object UserResponseMapper {

    fun PaginatedUserResponse.toUserList(): List<User> = this.users.map { userResponse ->
        User(
            id = userResponse.id,
            self = false,
            firstName = userResponse.firstName,
            lastName = userResponse.lastName,
            profilePictureUrl = userResponse.profilePictureUrl,
            email = userResponse.email,
        )
    }
}