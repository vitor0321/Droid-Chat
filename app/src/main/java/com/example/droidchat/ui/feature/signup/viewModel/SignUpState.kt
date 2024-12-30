package com.example.droidchat.ui.feature.signup.viewModel

import android.net.Uri

internal data class SignUpState(
    val profilePictureUri: Uri? = null,
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val passwordConfirmation: String = "",
    val passwordConfirmationError: String? = null,
    val passwordExtraText: String? = null,
    val isProfilePictureModalBottomSheetOpen: Boolean = false,
    val hasError: Boolean = false,
    val isLoading: Boolean = false,
)