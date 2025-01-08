package com.example.droidchat.ui.feature.signup.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow

@Composable
internal fun SignUpNavigationEffect(
    actions: SharedFlow<SignUpAction>,
    onSignInSuccess: () -> Unit,
) {

    LaunchedEffect(true) {
        actions.collect { action ->
            when (action) {
                is SignUpAction.NavigateToSignIn -> onSignInSuccess()
            }
        }
    }
}