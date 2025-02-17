package com.example.droidchat.data.service.mapper

import com.example.droidchat.data.network.model.request.CreateAccountRequest
import com.example.droidchat.domain.model.CreateAccount

internal object CreateAccountMapper {

    fun CreateAccount.toCreateAccountRequest() = CreateAccountRequest(
        email = email,
        password = password,
        firstName = firstName,
        lastName = lastName,
        profilePictureId = profilePictureUri,
    )
}