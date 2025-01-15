package com.example.droidchat.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.droidchat.ui.components.bottomNavigation.BottomNavigationMenu
import com.example.droidchat.ui.navigation.ChatNavHost
import com.example.droidchat.ui.navigation.rememberDroidChatNavigationState

@Composable
internal fun ChatApp() {
    val navigationController = rememberDroidChatNavigationState()

    Scaffold(
        bottomBar = {
            val topLevelDestination = navigationController.topLevelDestination.toTypedArray()
            if (topLevelDestination.contains(navigationController.currentTopLevelDestination)) {
                BottomNavigationMenu(navigationState = navigationController)
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
            ChatNavHost(navigationState = navigationController)
        }
    }
}