package com.walcker.droidchat.ui.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.walcker.droidchat.R
import com.walcker.droidchat.strings.strings
import com.walcker.droidchat.ui.components.PrimaryButton
import com.walcker.droidchat.ui.components.PrimaryTextField
import com.walcker.droidchat.ui.theme.BackgroundGradient
import com.walcker.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.ui.theme.DroidSpace

@Composable
internal fun SignInRoute(
    viewModel: SignInViewModel = viewModel(),
    navigateToSignUp: () -> Unit,
) {
    val formState = viewModel.formState

    SignInScreen(
        formState = formState,
        onEvent = viewModel::onEvent,
        onRegisterClick = navigateToSignUp,
    )
}

@Composable
internal fun SignInScreen(
    formState: SignInFormState,
    onEvent: (SignInFormEvent) -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DroidSpace.Medium.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.padding(DroidSpace.Large.value))

            PrimaryTextField(
                value = formState.email,
                onValueChange = { onEvent(SignInFormEvent.EmailChanged(it)) },
                placeholder = strings.signInStrings.featureLoginEmail,
                leadingIcon = R.drawable.ic_envelope,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                errorMessage = formState.emailError
            )

            Spacer(modifier = Modifier.padding(DroidSpace.XSmall.value))

            PrimaryTextField(
                value = formState.password,
                onValueChange = { onEvent(SignInFormEvent.PasswordChanged(it)) },
                placeholder = strings.signInStrings.featureLoginPassword,
                leadingIcon = R.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                errorMessage = formState.passwordError
            )

            Spacer(modifier = Modifier.padding(DroidSpace.Large.value))

            PrimaryButton(
                text = strings.signInStrings.featureLoginButton,
                isLoading = formState.isLoading,
                onClick = { onEvent(SignInFormEvent.Submit) },
            )

            Spacer(modifier = Modifier.padding(DroidSpace.Large.value))

            val noAccountRegisterText = strings.signInStrings.featureLoginNoAccount + strings.signInStrings.featureLoginRegister

            Text(
                text = buildAnnotatedString {
                    val registerTextStart = noAccountRegisterText.indexOf(strings.signInStrings.featureLoginRegister)
                    val registerTextEnd = registerTextStart + strings.signInStrings.featureLoginRegister.length

                    append(noAccountRegisterText)

                    addStyle(
                        style = SpanStyle(
                            color = Color.White,
                        ),
                        start = 0,
                        end = registerTextStart
                    )

                    addLink(
                        clickable = LinkAnnotation.Clickable(
                            tag = "register_text",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    textDecoration = TextDecoration.Underline
                                )
                            ),
                            linkInteractionListener = { onRegisterClick() }
                        ),
                        start = registerTextStart,
                        end = registerTextEnd,
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen(
            formState = SignInFormState(),
            onEvent = { },
            onRegisterClick = { },
        )
    }
}