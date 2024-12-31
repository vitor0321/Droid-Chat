package com.example.products.auth.ui.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.platform.theme.DroidChatTheme
import com.example.products.auth.ui.components.area.splash.SplashScreenArea
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
