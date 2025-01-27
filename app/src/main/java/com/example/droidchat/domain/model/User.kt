package com.example.droidchat.domain.model

internal data class User(
    val id: Int,
    val self: Boolean,
    val firstName: String,
    val lastName: String,
    val email: String,
    val profilePictureUrl: String?,
)