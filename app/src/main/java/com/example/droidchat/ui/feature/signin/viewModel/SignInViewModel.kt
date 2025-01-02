package com.example.droidchat.ui.feature.signin.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.domain.NetWorkDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor (
//    private val formValidator: FormValidator<SignInState>,
    private val netWorkDataSource: NetWorkDataSource,
) : ViewModel() {

    init {
        viewModelScope.launch {
            val response = netWorkDataSource.signIn(AuthRequest("username", "password"))
            // Handle response
        }
    }

    var state by mutableStateOf(SignInState())
        private set

    fun onFormEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged ->
                state = state.copy(email = event.email, emailError = null)

            is SignInEvent.PasswordChanged ->
                state = state.copy(password = event.password, passwordError = null)

            is SignInEvent.Submit -> doSignIn()
        }
    }

    private fun doSignIn() {
//        if (isValidForm()) {
//            state = state.copy(isLoading = true)
//            // Request to API
//        }
    }

//    private fun isValidForm(): Boolean {
//        return !formValidator.validate(state).also {
//            state = it
//        }.hasError
//    }
}