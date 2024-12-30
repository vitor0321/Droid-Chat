package com.example.droidchat.ui.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.droidchat.R
import com.example.droidchat.ui.components.PrimaryButton
import com.example.droidchat.ui.components.PrimaryTextField
import com.example.droidchat.ui.feature.signin.viewModel.SignInFormEvent
import com.example.droidchat.ui.feature.signin.viewModel.SignInFormState
import com.example.droidchat.ui.feature.signin.viewModel.SignInViewModel
import com.example.droidchat.ui.theme.BackgroundGradient
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.strings.strings

@Composable
internal fun SignInRoute(
    viewModel: SignInViewModel = viewModel(),
    navigateToSignUp: () -> Unit,
) {
    val formState = viewModel.formState
    SignInScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent,
        onRegisterClick = navigateToSignUp,
    )
}

@Composable
private fun SignInScreen(
    formState: SignInFormState,
    onFormEvent: (SignInFormEvent) -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(78.dp))

        PrimaryTextField(
            value = formState.email,
            onValueChange = {
                onFormEvent(SignInFormEvent.EmailChanged(it))
            },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium)),
            placeholder = strings.signInStrings.featureLoginEmail,
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
            errorMessage = formState.emailError
        )

        Spacer(modifier = Modifier.height(14.dp))

        PrimaryTextField(
            value = formState.password,
            onValueChange = {
                onFormEvent(SignInFormEvent.PasswordChanged(it))
            },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium)),
            placeholder = strings.signInStrings.featureLoginPassword,
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            errorMessage = formState.passwordError
        )

        Spacer(modifier = Modifier.height(98.dp))

        PrimaryButton(
            text = strings.signInStrings.featureLoginButton,
            onClick = {
                onFormEvent(SignInFormEvent.Submit)
            },
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium)),
            isLoading = formState.isLoading,
        )

        Spacer(modifier = Modifier.height(56.dp))

        val noAccountText = strings.signInStrings.featureLoginNoAccount
        val registerText = strings.signInStrings.featureLoginRegister
        val noAccountRegisterText = "$noAccountText $registerText"

        val annotatedString = buildAnnotatedString {
            val registerTextStartIndex = noAccountRegisterText.indexOf(registerText)
            val registerTextEndIndex = registerTextStartIndex + registerText.length

            append(noAccountRegisterText)

            addStyle(
                style = SpanStyle(
                    color = Color.White
                ),
                start = 0,
                end = registerTextStartIndex,
            )

            addLink(
                clickable = LinkAnnotation.Clickable(
                    tag = "register_text,",
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline,
                        )
                    ),
                    linkInteractionListener = {
                        onRegisterClick()
                    }
                ),
                start = registerTextStartIndex,
                end = registerTextEndIndex,
            )
        }

        Text(text = annotatedString)
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen(
            formState = SignInFormState(),
            onFormEvent = {},
            onRegisterClick = {},
        )
    }
}