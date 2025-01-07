package com.example.droidchat.ui.feature.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.droidchat.ui.components.area.shared.AppDialogArea
import com.example.droidchat.ui.components.area.signin.SigInScreenArea
import com.example.droidchat.ui.feature.signin.navigation.SignInNavigationEffect
import com.example.droidchat.ui.feature.signin.viewModel.SignInEvent
import com.example.droidchat.ui.feature.signin.viewModel.SignInState
import com.example.droidchat.ui.feature.signin.viewModel.SignInViewModel
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
    navigateToMessages: () -> Unit,
) {
    val state = viewModel.state
    val onFormEvent: (SignInEvent) -> Unit = remember { { viewModel.onFormEvent(it) } }

    SignInNavigationEffect(
        actions = viewModel.signInActionFlow,
        navigateToMessages = navigateToMessages,
        navigateToSignUp = navigateToSignUp,
    )

    SignInScreen(
        state = state,
        onFormEvent = onFormEvent,
    )

    if (state.errorMessage?.isNotEmpty() == true)
        AppDialogArea(
            message = state.errorMessage,
            onCloseDialog = { onFormEvent(SignInEvent.CloseErrorDialog) },
        )
}

@Composable
private fun SignInScreen(
    state: SignInState,
    onFormEvent: (SignInEvent) -> Unit,
) {
    SigInScreenArea(
        state = state,
        onFormEvent = onFormEvent,
    )
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen(
            state = SignInState(),
            onFormEvent = {},
        )
    }
}