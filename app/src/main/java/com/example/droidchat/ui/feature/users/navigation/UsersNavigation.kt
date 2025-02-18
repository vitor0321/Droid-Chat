package com.example.droidchat.ui.feature.users.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.droidchat.ui.navigation.Route

internal fun NavController.navigateToUser(
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.UsersRoute,
        navOptions = navOptions
    )
}