package com.example.droidchat.ui.navigation

import kotlinx.serialization.Serializable

internal sealed interface Route {
    @Serializable
    object SplashRoute

    @Serializable
    object SignInRoute

    @Serializable
    object SignUpRoute

    @Serializable
    object ChatsRoute
}