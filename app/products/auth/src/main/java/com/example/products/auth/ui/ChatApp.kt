package com.example.products.auth.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.products.auth.navigation.ChatNavHost

@Composable
internal fun ChatApp() {
    Scaffold(
        bottomBar = {
            // Vazio
        }
    ) { innerPaddings ->
        Box(
           modifier = Modifier
               .consumeWindowInsets(innerPaddings)
               .padding(innerPaddings)
               .imePadding()
               .fillMaxSize()
        ) {
            ChatNavHost()
        }
    }
}