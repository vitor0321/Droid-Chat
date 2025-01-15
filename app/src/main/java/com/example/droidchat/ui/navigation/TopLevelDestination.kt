package com.example.droidchat.ui.navigation

import androidx.annotation.DrawableRes
import com.example.droidchat.R
import com.example.droidchat.ui.strings.strings
import kotlin.reflect.KClass

internal enum class TopLevelDestination(
    val title: String?,
    @DrawableRes val icon: Int?,
    val route: KClass<*>,
) {

    Chats(
        title = strings.commonStrings.commonNavigationItemChats,
        icon = R.drawable.ic_bottom_nav_chats,
        route = Route.ChatsRoute::class
    ),
    PlusButton(
        title = null,
        icon = null,
        route = Route.UsersRoute::class
    ),
    Profile(
        title = strings.commonStrings.commonNavigationItemProfile,
        icon = R.drawable.ic_bottom_nav_profile,
        route = Route.ProfileRoute::class
    ),
}