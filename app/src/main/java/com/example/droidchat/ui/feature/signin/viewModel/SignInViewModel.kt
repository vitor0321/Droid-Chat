package com.example.droidchat.ui.feature.signin.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.droidchat.strings.strings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(SignInState())
        private set

    fun onFormEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> {
                state = state.copy(email = event.email, emailError = null)
            }

            is SignInEvent.PasswordChanged -> {
                state = state.copy(password = event.password, passwordError = null)
            }

            SignInEvent.Submit -> {
                doSignIn()
            }
        }
    }

    private fun doSignIn() {
        var isFormValid = true
        // resetFormErrorState()
        if (state.email.isBlank()) {
            state = state.copy(emailError = strings.errorMessagesStrings.errorMessageEmailInvalid)
            isFormValid = false
        }

        if (state.password.isBlank()) {
            state = state.copy(passwordError = strings.errorMessagesStrings.errorMessagePasswordInvalid)
            isFormValid = false
        }

        if (isFormValid) {
            state = state.copy(isLoading = true)
            // Request to API
        }
    }

    private fun resetFormErrorState() {
        state = state.copy(
            emailError = null,
            passwordError = null,
        )
    }
}