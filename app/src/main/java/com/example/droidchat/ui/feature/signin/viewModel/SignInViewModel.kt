package com.example.droidchat.ui.feature.signin.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.data.network.model.NetworkException
import com.example.droidchat.domain.AuthRepository
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.validator.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor (
    private val formValidator: FormValidator<SignInState>,
    private val authRepository: AuthRepository,
) : ViewModel() {

    var state by mutableStateOf(SignInState())
        private set

    fun onFormEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged ->
                state = state.copy(email = event.email, emailError = null)

            is SignInEvent.PasswordChanged ->
                state = state.copy(password = event.password, passwordError = null)

            is SignInEvent.Submit -> doSignIn()

            is SignInEvent.CloseErrorDialog ->
                state = state.copy(errorMessage = null)
        }
    }

    private fun doSignIn() {
        if (isValidForm()) {
            state = state.copy(isLoading = true)
            viewModelScope.launch {
                authRepository.signIn(email = state.email, password = state.password).fold(
                    onSuccess = {
                        state = state.copy(
                            isSignedSuccess = true,
                            isLoading = false
                        )
                    },
                    onFailure = { handleSignUpError(exception = it) }
                )
            }
        }
    }

    private fun handleSignUpError(exception: Throwable) {
        state = state.copy(
            isLoading = false,
            errorMessage = if (exception is NetworkException.ApiException) {
                when (exception.statusCode) {
                    401 -> strings.errorMessagesStrings.errorMessageInvalidUsernameOrPassword
                    500 -> strings.errorMessagesStrings.commonServiceUnavailable
                    else -> strings.errorMessagesStrings.commonGenericErrorMessage
                }
            } else strings.errorMessagesStrings.commonGenericErrorMessage
        )
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(state).also {
            state = it
        }.hasError
    }
}