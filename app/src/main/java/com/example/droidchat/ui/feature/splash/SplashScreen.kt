package com.example.droidchat.ui.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import com.example.droidchat.ui.components.area.splash.SplashScreenArea
import com.example.droidchat.ui.feature.splash.navigation.SplashNavigationEffect
import com.example.droidchat.ui.feature.splash.viewModel.SplashViewModel
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavigateToSignIn: () -> Unit,
    onNavigateToHome: () -> Unit,
    onCloseApp: () -> Unit,
) {

    LifecycleStartEffect(Unit) {
        viewModel.checkSession()
        onStopOrDispose { }
    }

    SplashNavigationEffect(
        actions = viewModel.splashActionFlow,
        onNavigateToSignIn = onNavigateToSignIn,
        onNavigateToHome = onNavigateToHome,
        onCloseApp = onCloseApp,
    )

    SplashScreen()
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
