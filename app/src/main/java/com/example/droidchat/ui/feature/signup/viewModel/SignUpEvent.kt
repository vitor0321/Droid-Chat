package com.example.droidchat.ui.feature.signup.viewModel

import android.net.Uri

internal sealed interface SignUpEvent {
    data class ProfilePhotoUriChanged(val uri: Uri?) : SignUpEvent
    data class FirstNameChanged(val firstName: String) : SignUpEvent
    data class LastNameChanged(val lastName: String) : SignUpEvent
    data class EmailChanged(val email: String) : SignUpEvent
    data class PasswordChanged(val password: String) : SignUpEvent
    data class PasswordConfirmationChanged(val passwordConfirmation: String) : SignUpEvent
    data object OpenProfilePictureOptionsModalBottomSheet : SignUpEvent
    data object CloseProfilePictureOptionsModalBottomSheet : SignUpEvent
    data object Submit : SignUpEvent
}