package com.example.droidchat.ui.feature.components.field.sigup

import android.net.Uri
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
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace
import com.example.droidchat.ui.strings.strings

@Composable
internal fun BodySigUpField(
    profilePictureUri: Uri?,
    firstName: String,
    firstNameError: String?,
    lastName: String,
    lastNameError: String?,
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    passwordConfirmation: String,
    passwordConfirmationError: String?,
    passwordExtraText: String?,
    onFormEvent: (SignUpEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfilePictureSelector(
            imageUri = profilePictureUri,
            modifier = Modifier
                .clickable { onFormEvent(SignUpEvent.OpenProfilePictureOptionsModalBottomSheet) }
        )

        Spacer(modifier = Modifier.height(DroidSpace.MLarge.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpFirstName,
            value = firstName,
            onValueChange = { onFormEvent(SignUpEvent.FirstNameChanged(it)) },
            errorText = firstNameError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpLastName,
            value = lastName,
            onValueChange = { onFormEvent(SignUpEvent.LastNameChanged(it)) },
            errorText = lastNameError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpEmail,
            value = email,
            onValueChange = { onFormEvent(SignUpEvent.EmailChanged(it)) },
            keyboardType = KeyboardType.Email,
            errorText = emailError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpPassword,
            value = password,
            onValueChange = { onFormEvent(SignUpEvent.PasswordChanged(it)) },
            keyboardType = KeyboardType.Password,
            extraText = passwordExtraText,
            errorText = passwordError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

        SecondaryTextField(
            label = strings.signUpStrings.featureSignUpPasswordConfirmation,
            value = passwordConfirmation,
            onValueChange = { onFormEvent(SignUpEvent.PasswordConfirmationChanged(it)) },
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            extraText = passwordExtraText,
            errorText = passwordConfirmationError,
        )

        Spacer(modifier = Modifier.height(DroidSpace.MLarge.value))

        PrimaryButton(
            text = strings.signUpStrings.featureSignUpButton,
            onClick = { onFormEvent(SignUpEvent.Submit) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodySigInFieldPreview() {
    DroidChatTheme {
        BodySigUpField(
            profilePictureUri = null,
            firstName = "",
            firstNameError = "",
            lastName = "",
            lastNameError = "",
            email = "",
            emailError = "",
            password = "",
            passwordError = "",
            passwordConfirmation = "",
            passwordConfirmationError = "",
            passwordExtraText = "",
            onFormEvent = {}
        )
    }
}