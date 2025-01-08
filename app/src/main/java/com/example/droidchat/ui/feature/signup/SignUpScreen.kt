package com.example.droidchat.ui.feature.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.droidchat.ui.components.area.shared.AppDialogArea
import com.example.droidchat.ui.components.area.signup.SignUpScreenArea
import com.example.droidchat.ui.feature.signup.navigation.SignUpNavigationEffect
import com.example.droidchat.ui.feature.signup.viewModel.SignUpEvent
import com.example.droidchat.ui.feature.signup.viewModel.SignUpState
import com.example.droidchat.ui.feature.signup.viewModel.SignUpViewModel
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignInSuccess: () -> Unit,
) {
    val state = viewModel.state
    val onFormEvent: (SignUpEvent) -> Unit = remember { { viewModel.onFormEvent(it) } }

    SignUpNavigationEffect(
        actions = viewModel.signUpActionFlow,
        onSignInSuccess = onSignInSuccess
    )

    SignUpScreen(
        state = state,
        onFormEvent = onFormEvent
    )

    state.showDialogSignIn.takeIf { it }?.let {
        AppDialogArea(
            message = strings.signUpStrings.featureSignUpSuccess,
            onCloseDialog = { onFormEvent(SignUpEvent.OnSignIn) },
        )
    }

    state.apiErrorMessage?.let {
        AppDialogArea(
            title = strings.errorMessagesStrings.commonGenericErrorTitle,
            message = it,
            onCloseDialog = { onFormEvent(SignUpEvent.AlertDialogDismiss) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreen(
    state: SignUpState,
    onFormEvent: (SignUpEvent) -> Unit,
) {
    SignUpScreenArea(
        state = state,
        onFormEvent = onFormEvent
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DroidChatTheme {
        SignUpScreen(
            state = SignUpState(),
            onFormEvent = {},
        )
    }
}