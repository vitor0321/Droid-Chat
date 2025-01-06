package com.example.droidchat.ui.feature.signin.viewModel

internal data class SignInState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val isSignedSuccess: Boolean = false,
    val errorMessage: String? = null,
    val toastMessage: String? = null,
)