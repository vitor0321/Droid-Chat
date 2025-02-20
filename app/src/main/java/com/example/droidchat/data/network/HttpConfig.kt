package com.example.droidchat.data.network

internal enum class HttpConfig(val value: String) {
    API("https://chat-api.androidmoderno.com.br"),
    AUTHORIZATION("Authorization"),
    BEARER("Bearer"),
    SIGN_IN("/signin"),
    SIGN_UP("/signup"),
    AUTHENTICATE("/authenticate"),
    UPLOADING("/profile-picture"),
    CONVERSATIONS("/conversations"),
    USERS("/users"),
}