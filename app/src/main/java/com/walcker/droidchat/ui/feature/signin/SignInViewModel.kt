package com.walcker.droidchat.ui.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.walcker.droidchat.strings.strings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor() : ViewModel() {

    var formState by mutableStateOf(SignInFormState())
        private set

    fun onEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> formState = formState.copy(email = event.email, emailError = null)
            is SignInFormEvent.PasswordChanged -> formState = formState.copy(password = event.password, passwordError = null)
            is SignInFormEvent.Submit -> doSignIn()
        }
    }

    private fun doSignIn() {
        var isFormValid = true
//        resetFormErrorState()
        if (formState.email.isBlank()) {
            formState = formState.copy(emailError = strings.errorMessagesStrings.errorMessageEmailInvalid)
            isFormValid = false
        }

        if (formState.password.isBlank()) {
            formState = formState.copy(passwordError = strings.errorMessagesStrings.errorMessagePasswordInvalid)
            isFormValid = false
        }

        if (isFormValid)
            formState = formState.copy(isLoading = true)
    }

    private fun resetFormErrorState() {
        formState = formState.copy(
            emailError = null,
            passwordError = null,
        )
    }
}