package com.example.droidchat.ui.feature.signin.viewModel

import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.validator.EmailValidator
import com.example.droidchat.ui.validator.FormValidator
import com.example.droidchat.ui.validator.PasswordValidator
import javax.inject.Inject

internal class SignInFormValidator @Inject constructor() : FormValidator<SignInState> {
    override fun validate(formState: SignInState): SignInState {

        val isEmailValid = EmailValidator.isValid(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)

        val hasError = listOf(
            isEmailValid,
            isPasswordValid,
        ).any { !it }

        return formState.copy(
            emailError = if (!isEmailValid) strings.errorMessagesStrings.errorMessageEmailInvalid else null,
            passwordError = if (!isPasswordValid) strings.errorMessagesStrings.errorMessagePasswordInvalid else null,
            hasError = hasError,
        )
    }
}