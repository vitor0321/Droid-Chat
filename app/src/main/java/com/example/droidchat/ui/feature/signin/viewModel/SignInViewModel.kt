package com.example.droidchat.ui.feature.signin.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.data.network.model.exception.NetworkException
import com.example.droidchat.domain.AuthService
import com.example.droidchat.ui.extension.validator.FormValidator
import com.example.droidchat.ui.feature.signin.navigation.SignInAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor (
    private val formValidator: FormValidator<SignInState>,
    private val authService: AuthService,
) : ViewModel() {

    var state by mutableStateOf(SignInState())
        private set

    private val _signInActionFlow = MutableSharedFlow<SignInAction>()
    val signInActionFlow = _signInActionFlow.asSharedFlow()

    fun onFormEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged ->
                state = state.copy(email = event.email, emailError = null)

            is SignInEvent.PasswordChanged ->
                state = state.copy(password = event.password, passwordError = null)

            is SignInEvent.Submit -> doSignIn()

            is SignInEvent.CloseErrorDialog ->
                state = state.copy(errorMessage = null)

            is SignInEvent.OnSignUp ->
                viewModelScope.launch { _signInActionFlow.emit(SignInAction.NavigateToSignUp) }
        }
    }

    private fun doSignIn() {
        if (isValidForm()) {
            state = state.copy(isLoading = true)
            viewModelScope.launch {
                authService.signIn(email = state.email, password = state.password).fold(
                    onSuccess = {
                        state = state.copy(isLoading = false)
                        _signInActionFlow.emit(SignInAction.Success)
                    },
                    onFailure = { handleSignUpError(exception = it) }
                )
            }
        }
    }

    private suspend fun handleSignUpError(exception: Throwable) {
        state = state.copy(isLoading = false)
        val error = if (exception is NetworkException.ApiException && exception.statusCode == 401)
            SignInAction.Error.UnauthorizedError
        else SignInAction.Error.GenericError

        _signInActionFlow.emit(error)
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(state).also {
            state = it
        }.hasError
    }
}