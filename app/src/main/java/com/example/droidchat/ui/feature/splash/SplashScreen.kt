package com.example.droidchat.ui.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.feature.components.area.splash.SplashScreenArea
import com.example.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.delay

@Composable
internal fun SplashRoute(
    onNavigateToSignIn: () -> Unit,
) {
    SplashScreen()
    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToSignIn()
    }
}

@Composable
private fun SplashScreen() {
    SplashScreenArea()
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF00BCD4)
internal fun SplashScreenPreview() {
    DroidChatTheme {
        SplashScreen()
    }
}
