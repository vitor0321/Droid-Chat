package com.example.droidchat.ui.feature.signin.viewModel

internal data class SignInFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
)