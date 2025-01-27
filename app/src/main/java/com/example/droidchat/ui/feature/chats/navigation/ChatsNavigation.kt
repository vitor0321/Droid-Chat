package com.example.droidchat.ui.feature.chats.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.droidchat.ui.navigation.Route

internal fun NavController.navigateToChats(
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.ChatsRoute,
        navOptions = navOptions
    )
}