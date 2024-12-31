package com.example.products.auth.ui.feature.signin.viewModel

internal data class SignInState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
)