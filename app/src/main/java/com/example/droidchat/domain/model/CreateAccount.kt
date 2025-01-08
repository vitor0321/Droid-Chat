package com.example.droidchat.domain.model

data class CreateAccount(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePictureUri: String?,
)