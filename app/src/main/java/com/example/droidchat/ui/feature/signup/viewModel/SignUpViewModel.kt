package com.example.droidchat.ui.feature.signup.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.data.network.model.NetworkException
import com.example.droidchat.domain.AuthRepository
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.validator.FormValidator
import com.example.droidchat.util.image.ImageCompressor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignUpViewModel @Inject constructor(
    private val formValidator: FormValidator<SignUpState>,
    private val authRepository: AuthRepository,
    private val imageCompressor: ImageCompressor,
) : ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    fun onFormEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.ProfilePhotoUriChanged -> {
                state = state.copy(profilePictureUri = event.uri)
                event.uri?.let { compressImageAndUpdateState(it) }
            }

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

            is SignUpEvent.AlertDialogDismiss -> state = state.copy(apiErrorMessage = null)

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

    private fun compressImageAndUpdateState(uri: Uri) {
        viewModelScope.launch {
            try {
                state = state.copy(isCompressingImage = true)
                val compressedFile = imageCompressor.compressAndResizeImage(imageUri = uri)
                state = state.copy(profilePictureUri = compressedFile.toUri())
            } catch (e: Exception) {
                // Log error
            } finally {
                state = state.copy(isCompressingImage = false)
            }
        }
    }

    private fun doSignUp() {
        if (isValidForm()) {
            state = state.copy(isLoading = true)
            viewModelScope.launch {
                authRepository.signUp(
                    createAccount = CreateAccount(
                        username = "",
                        password = "",
                        firstName = state.firstName,
                        lastName = state.lastName,
                        profilePictureUri = null,
                    )
                ).fold(
                    onSuccess = { state = state.copy(isLoading = false, isSignedUp = true) },
                    onFailure = { handleSignUpError(it) }
                )
            }
        }
    }

    private fun handleSignUpError(exception: Throwable) {
        state = state.copy(
            isLoading = false,
            apiErrorMessage = if (exception is NetworkException.ApiException) {
                when (exception.statusCode) {
                    400 -> strings.errorMessagesStrings.errorMessageApiFormValidationFailed
                    409 -> strings.errorMessagesStrings.errorMessageUserWithUsernameAlreadyExists
                    else -> strings.errorMessagesStrings.commonGenericErrorTitle
                }
            } else strings.errorMessagesStrings.commonGenericErrorTitle
        )
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(state).also {
            state = it
        }.hasError
    }
}