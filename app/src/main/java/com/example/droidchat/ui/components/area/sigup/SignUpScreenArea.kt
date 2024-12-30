package com.example.droidchat.ui.components.area.sigup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.field.sigup.BodySigUpField
import com.example.droidchat.ui.components.field.sigup.BottomSheetSigUpField
import com.example.droidchat.ui.components.field.sigup.HeadSignUpField
import com.example.droidchat.ui.feature.signup.viewModel.SignUpEvent
import com.example.droidchat.ui.feature.signup.viewModel.SignUpState
import com.example.platform.theme.BackgroundGradient
import com.example.droidchat.DroidChatTheme

@Composable
internal fun SignUpScreenArea(
    state: SignUpState,
    onFormEvent: (SignUpEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState()),
    ) {
        HeadSignUpField(
            bodySignUp = {
                BodySigUpField(
                    profilePictureUri = state.profilePictureUri,
                    firstName = state.firstName,
                    firstNameError = state.firstNameError,
                    lastName = state.lastName,
                    lastNameError = state.lastNameError,
                    email = state.email,
                    emailError = state.emailError,
                    password = state.password,
                    passwordError = state.passwordError,
                    passwordConfirmation = state.passwordConfirmation,
                    passwordConfirmationError = state.passwordConfirmationError,
                    passwordExtraText = state.passwordExtraText,
                    onFormEvent = onFormEvent,
                )
            },
            bottomSheetSigInField = {
                BottomSheetSigUpField(
                    isProfilePictureModalBottomSheetOpen = state.isProfilePictureModalBottomSheetOpen,
                    onFormEvent = onFormEvent,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenAreaPreview() {
    DroidChatTheme {
        SignUpScreenArea(
            state = SignUpState(),
            onFormEvent = {},
        )
    }
}