package com.example.droidchat.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.droidchat.ui.components.bottomNavigation.BottomNavigationMenu
import com.example.droidchat.ui.navigation.ChatNavHost
import com.example.droidchat.ui.navigation.DroidChatNavigationState
import com.example.droidchat.ui.navigation.rememberDroidChatNavigationState

@Composable
internal fun ChatApp(
    navigationState: DroidChatNavigationState = rememberDroidChatNavigationState()
) {

    val topLevelDestination = remember(navigationState.topLevelDestination) {
        navigationState.topLevelDestination.toSet()
    }

    Scaffold(
        bottomBar = {
            if (navigationState.currentTopLevelDestination in topLevelDestination) {
                BottomNavigationMenu(navigationState = navigationState)
            }
        },
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .consumeWindowInsets(innerPaddings)
                .padding(innerPaddings)
                .imePadding()
                .fillMaxSize()
        ) {
            ChatNavHost(navigationState = navigationState)
        }
    }
}