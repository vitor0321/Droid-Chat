package com.example.droidchat.ui.feature.signin

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.droidchat.ui.components.area.signin.SigInScreenArea
import com.example.droidchat.ui.components.dialog.AppDialog
import com.example.droidchat.ui.feature.signin.viewModel.SignInEvent
import com.example.droidchat.ui.feature.signin.viewModel.SignInState
import com.example.droidchat.ui.feature.signin.viewModel.SignInViewModel
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun SignInRoute(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
    navigateToMessage: () -> Unit,
) {
    val state = viewModel.state
    val onFormEvent: (SignInEvent) -> Unit = remember { { viewModel.onFormEvent(it) } }

    SignInScreen(
        state = state,
        onRegisterClick = navigateToSignUp,
        onFormEvent = onFormEvent,
    )

    state.isSignedSuccess.takeIf { it }?.let {
        navigateToMessage()
    }

    if (state.errorMessage?.isNotEmpty() == true)
        AppDialog(
            message = state.errorMessage,
            onEventDismiss = { onFormEvent(SignInEvent.CloseErrorDialog) },
            onEventConfirm = { onFormEvent(SignInEvent.CloseErrorDialog) },
        )

    if (state.toastMessage?.isNotEmpty() == true)
        Toast.makeText(null, state.toastMessage, Toast.LENGTH_SHORT).show()
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