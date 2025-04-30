package com.example.droidchat.ui.feature.signup.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.data.model.exception.NetworkException
import com.example.droidchat.domain.AuthService
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.ui.extension.validator.FormValidator
import com.example.droidchat.ui.feature.signup.navigation.SignUpAction
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.util.handleError
import com.example.droidchat.util.image.ImageCompressor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignUpViewModel @Inject constructor(
    private val formValidator: FormValidator<SignUpState>,
    private val authService: AuthService,
    private val imageCompressor: ImageCompressor,
) : ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    private val _signUpActionFlow = MutableSharedFlow<SignUpAction>()
    val signUpActionFlow = _signUpActionFlow.asSharedFlow()

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

            is SignUpEvent.OnSignIn ->
                viewModelScope.launch {
                    _signUpActionFlow.emit(SignUpAction.NavigateToSignIn)
                }
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
                authService.uploadProfilePicture(state.profilePictureUri?.path.orEmpty()).fold(
                    onSuccess = { image -> signUp(profilePictureId = image.id) },
                    onFailure = { exception ->
                        handleError(
                            exception = exception,
                            isLoading = { state = state.copy(isLoading = it) },
                            emitError = { state = state.copy(apiErrorMessage = strings.errorMessagesStrings.errorMessageApiFormUploadImageFailed) }
                        )
                    }
                )
            }
        }
    }

    private suspend fun signUp(profilePictureId: Int) {
        authService.signUp(
            createAccount = CreateAccount(
                email = state.email,
                password = state.password,
                firstName = state.firstName,
                lastName = state.lastName,
                profilePictureUri = profilePictureId,
            )
        ).fold(
            onSuccess = { state = state.copy(isLoading = false, showDialogSignIn = true) },
            onFailure = { exception -> handleSignUpError(exception) }
        )
    }

    private suspend fun handleSignUpError(exception: Throwable) {
        handleError(
            exception = exception,
            isLoading = { state = state.copy(isLoading = it) },
            emitError = {
                if (exception is NetworkException.ApiException) {
                    when (exception.statusCode) {
                        400 -> strings.errorMessagesStrings.errorMessageApiFormValidationFailed
                        409 -> strings.errorMessagesStrings.errorMessageUserWithUsernameAlreadyExists
                        else -> strings.errorMessagesStrings.commonGenericErrorMessage
                    }
                }
                state = state.copy(apiErrorMessage = it)
            }
        )
    }

    private fun isValidForm(): Boolean {
        return !formValidator.validate(state).also {
            state = it
        }.hasError
    }
}