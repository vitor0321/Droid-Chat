package com.example.droidchat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.droidchat.ui.feature.chats.navigation.navigateToChats

@Composable
internal fun rememberDroidChatNavigationState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) { DroidChatNavigationState(navController) }

@Stable
internal class DroidChatNavigationState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable
        get() = TopLevelDestination.entries.firstOrNull { topLevelDestination ->
            currentDestination?.hasRoute(topLevelDestination.route) == true
        }

    val topLevelDestination = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.Chats -> navController.navigateToChats(topLevelNavOptions)
            TopLevelDestination.PlusButton -> {}
            TopLevelDestination.Profile -> {}
        }
    }
}