package com.example.droidchat.ui.feature.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.droidchat.DroidChatTheme
import com.example.droidchat.ui.components.area.signin.SigInScreenArea
import com.example.droidchat.ui.feature.signin.viewModel.SignInEvent
import com.example.droidchat.ui.feature.signin.viewModel.SignInFormValidator
import com.example.droidchat.ui.feature.signin.viewModel.SignInState
import com.example.droidchat.ui.feature.signin.viewModel.SignInViewModel

@Composable
internal fun SignInRoute(
    viewModel: SignInViewModel = viewModel {
        SignInViewModel(formValidator = SignInFormValidator())
    },
    navigateToSignUp: () -> Unit,
) {
    val state = viewModel.state
    SignInScreen(
        state = state,
        onRegisterClick = navigateToSignUp,
        onFormEvent = viewModel::onFormEvent,
    )
}

@Composable
private fun SignInScreen(
    state: SignInState,
    onRegisterClick: () -> Unit,
    onFormEvent: (SignInEvent) -> Unit,
) {
    SigInScreenArea(
        state = state,
        onFormEvent = onFormEvent,
        onRegisterClick = onRegisterClick,
    )
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen(
            state = SignInState(),
            onFormEvent = {},
            onRegisterClick = {},
        )
    }
}