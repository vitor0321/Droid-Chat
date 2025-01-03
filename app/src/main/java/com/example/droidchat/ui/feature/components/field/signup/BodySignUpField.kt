package com.example.droidchat.ui.feature.components.field.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.ui.feature.components.field.shared.PrimaryButton
import com.example.droidchat.ui.feature.components.field.shared.ProfilePictureSelector
import com.example.droidchat.ui.feature.components.field.shared.SecondaryTextField
import com.example.droidchat.ui.feature.signup.viewModel.SignUpEvent
import com.example.droidchat.ui.feature.signup.viewModel.SignUpState
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace

@Composable
internal fun BodySigUpField(
    state: SignUpState,
    onFormEvent: (SignUpEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfilePictureSelector(
            imageUri = state.profilePictureUri,
            isCompressingImage = state.isCompressingImage,
            modifier = Modifier
                .clickable { onFormEvent(SignUpEvent.OpenProfilePictureOptionsModalBottomSheet) }
        )

        Spacer(modifier = Modifier.height(DroidSpace.MLarge.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpFirstName,
            value = state.firstName,
            onValueChange = { onFormEvent(SignUpEvent.FirstNameChanged(it)) },
            errorText = state.firstNameError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpLastName,
            value = state.lastName,
            onValueChange = { onFormEvent(SignUpEvent.LastNameChanged(it)) },
            errorText = state.lastNameError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpEmail,
            value = state.email,
            onValueChange = { onFormEvent(SignUpEvent.EmailChanged(it)) },
            keyboardType = KeyboardType.Email,
            errorText = state.emailError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpPassword,
            value = state.password,
            onValueChange = { onFormEvent(SignUpEvent.PasswordChanged(it)) },
            keyboardType = KeyboardType.Password,
            extraText = state.passwordExtraText,
            errorText = state.passwordError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpPasswordConfirmation,
            value = state.passwordConfirmation,
            onValueChange = { onFormEvent(SignUpEvent.PasswordConfirmationChanged(it)) },
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            extraText = state.passwordExtraText,
            errorText = state.passwordConfirmationError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.MLarge.value))

        PrimaryButton(
            text = strings.signUpStrings.featureSignUpButton,
            isLoading = state.isLoading,
            onClick = { onFormEvent(SignUpEvent.Submit) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodySigInFieldPreview() {
    DroidChatTheme {
        BodySigUpField(
            state = SignUpState(),
            onFormEvent = {}
        )
    }
}