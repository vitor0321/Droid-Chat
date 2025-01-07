package com.example.droidchat.ui.feature.signin.viewModel

internal sealed interface SignInEvent {
    data class EmailChanged(val email: String) : SignInEvent
    data class PasswordChanged(val password: String) : SignInEvent
    data object Submit : SignInEvent
    data object CloseErrorDialog : SignInEvent
    data object OnSignUp : SignInEvent
}