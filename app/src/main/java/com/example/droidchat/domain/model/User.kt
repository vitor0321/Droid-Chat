package com.example.droidchat.domain.model

internal data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val profilePictureUrl: String?,
    val email: String,
)