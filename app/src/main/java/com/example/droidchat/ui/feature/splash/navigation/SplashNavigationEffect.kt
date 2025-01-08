package com.example.droidchat.ui.feature.splash.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.droidchat.ui.components.area.shared.AppDialogArea
import com.example.droidchat.ui.strings.strings
import kotlinx.coroutines.flow.SharedFlow

@Composable
internal fun SplashNavigationEffect(
    actions: SharedFlow<SplashAction>,
    onNavigateToSignIn: () -> Unit,
    onNavigateToHome: () -> Unit,
    onCloseApp: () -> Unit,
) {

    val showErrorDialog = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        actions.collect { action ->
            when (action) {
                is SplashAction.NavigateToSignIn -> onNavigateToHome()
                is SplashAction.UserNotAuthenticated -> onNavigateToSignIn()
                is SplashAction.ShowErrorDialog -> showErrorDialog.value = true
            }
        }
    }

    if (showErrorDialog.value)
        AppDialogArea(
            message = strings.errorMessagesStrings.errorMessageUnexpected,
            confirmButtonText = strings.errorMessagesStrings.errorBottomUnexpected,
            onCloseDialog = {
                showErrorDialog.value = false
                onCloseApp()
            },
            onEventDismiss = { }
        )
}