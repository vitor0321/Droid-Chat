package com.example.droidchat.ui.feature.signup.viewModel

import com.example.droidchat.ui.validator.EmailValidator
import com.example.droidchat.ui.validator.FormValidator
import com.example.droidchat.ui.validator.PasswordValidator
import com.example.droidchat.strings.strings

internal class SignUpFormValidator : FormValidator<SignUpState> {

    override fun validate(formState: SignUpState): SignUpState {
        val isFirstNameValid = formState.firstName.isNotEmpty()
        val isLastNameValid = formState.lastName.isNotEmpty()
        val isEmailValid = EmailValidator.isValid(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)
        val isPasswordConfirmationValid = PasswordValidator.isValid(formState.passwordConfirmation)
                && formState.passwordConfirmation == formState.password

        val hasError = listOf(
            isFirstNameValid,
            isLastNameValid,
            isEmailValid,
            isPasswordValid,
            isPasswordConfirmationValid,
        ).any { !it }

        return formState.copy(
            firstNameError = if (!isFirstNameValid) strings.errorMessagesStrings.errorMessageFieldBlank(
                strings.signUpStrings.featureSignUpFirstName
            ) else null,
            lastNameError = if (!isLastNameValid) strings.errorMessagesStrings.errorMessageFieldBlank(
                strings.signUpStrings.featureSignUpLastName
            ) else null,
            emailError = if (!isEmailValid) strings.errorMessagesStrings.errorMessageEmailInvalid else null,
            passwordError = if (!isPasswordValid) strings.errorMessagesStrings.errorMessagePasswordInvalid else null,
            passwordConfirmationError = if (!isPasswordConfirmationValid) strings.errorMessagesStrings.errorMessagePasswordConfirmationInvalid else null,
            hasError = hasError,
        )
    }
}