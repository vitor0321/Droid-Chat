package com.example.droidchat.data.mapper

import com.example.droidchat.data.model.response.PaginatedUserResponse
import com.example.droidchat.data.model.response.UserResponse
import com.example.droidchat.domain.model.User

internal object UserResponseMapper {

    fun PaginatedUserResponse.toUserList(): List<User> = this.users.map { userResponse ->
        userResponse.toUser()
    }

    fun UserResponse.toUser(selfUserIdi: Int? = null) = User(
        id = id,
        self = id == selfUserIdi,
        firstName = firstName,
        lastName = lastName,
        profilePictureUrl = profilePictureUrl,
        email = email
    )
}