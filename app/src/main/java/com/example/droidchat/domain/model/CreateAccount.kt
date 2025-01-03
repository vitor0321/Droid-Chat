package com.example.droidchat.domain.model

internal data class CreateAccount(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePictureUri: Int?,
)