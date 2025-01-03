package com.example.droidchat.data.repository.mapper

import com.example.droidchat.data.network.model.CreateAccountRequest
import com.example.droidchat.domain.model.CreateAccount

internal object CreateAccountMapper {

    fun CreateAccount.toCreateAccountRequest() = CreateAccountRequest(
        username = email,
        password = password,
        firstName = firstName,
        lastName = lastName,
        profilePictureId = profilePictureUri,
    )
}