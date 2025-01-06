package com.example.droidchat.ui.components.field.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.R
import com.example.droidchat.ui.components.field.shared.PrimaryButton
import com.example.droidchat.ui.components.field.shared.PrimaryTextField
import com.example.droidchat.ui.feature.signin.viewModel.SignInEvent
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace

@Composable
internal fun BodySigInField(
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    isLoading: Boolean,
    onFormEvent: (SignInEvent) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(DroidSpace.XExtraLarge.value))

        PrimaryTextField(
            modifier = Modifier.padding(horizontal = DroidSpace.MMedium.value),
            value = email,
            onValueChange = { onFormEvent(SignInEvent.EmailChanged(it)) },
            placeholder = strings.signInStrings.featureLoginEmail,
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
            errorMessage = emailError
        )

        Spacer(modifier = Modifier.height(DroidSpace.Medium.value))

        PrimaryTextField(
            modifier = Modifier.padding(horizontal = DroidSpace.MMedium.value),
            value = password,
            onValueChange = { onFormEvent(SignInEvent.PasswordChanged(it)) },
            placeholder = strings.signInStrings.featureLoginPassword,
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            errorMessage = passwordError
        )

        Spacer(modifier = Modifier.height(DroidSpace.XExtraLarge.value))

        PrimaryButton(
            modifier = Modifier.padding(horizontal = DroidSpace.MMedium.value),
            text = strings.signInStrings.featureLoginButton,
            onClick = { onFormEvent(SignInEvent.Submit) },
            isLoading = isLoading,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodySigInFieldPreview() {
    DroidChatTheme {
        BodySigInField(
            email = "",
            emailError = "",
            password = "",
            passwordError = "",
            isLoading = false,
            onFormEvent = {}
        )
    }
}