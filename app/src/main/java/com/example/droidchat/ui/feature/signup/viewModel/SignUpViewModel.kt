package com.example.droidchat.ui.feature.signup.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.droidchat.ui.validator.FormValidator
import com.example.droidchat.strings.strings

internal class SignUpViewModel(
    private val formValidator: FormValidator<SignUpState>
) : ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    fun onFormEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.ProfilePhotoUriChanged ->
                state = state.copy(profilePictureUri = event.uri)

            is SignUpEvent.FirstNameChanged ->
                state = state.copy(firstName = event.firstName)

            is SignUpEvent.LastNameChanged ->
                state = state.copy(lastName = event.lastName)

            is SignUpEvent.EmailChanged ->
                state = state.copy(email = event.email)

            is SignUpEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
                updatePasswordExtraText()
            }

            is SignUpEvent.PasswordConfirmationChanged -> {
                state = state.copy(passwordConfirmation = event.passwordConfirmation)
                updatePasswordExtraText()
            }

            is SignUpEvent.OpenProfilePictureOptionsModalBottomSheet ->
                state = state.copy(isProfilePictureModalBottomSheetOpen = true)

            is SignUpEvent.CloseProfilePictureOptionsModalBottomSheet ->
                state = state.copy(isProfilePictureModalBottomSheetOpen = false)

            is SignUpEvent.Submit -> doSignUp()
        }
    }

    private fun updatePasswordExtraText() {
        state = state.copy(
            passwordExtraText = if (state.password.isNotEmpty()
                && state.password == state.passwordConfirmation
            ) {
                strings.signUpStrings.featureSignUpPasswordsMatch
            } else null
        )
    }

    private fun doSignUp() {
        if (isValidForm()) {
            state = state.copy(isLoading = true)
            // Request to API
        }
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(state).also {
            state = it
        }.hasError
    }
}