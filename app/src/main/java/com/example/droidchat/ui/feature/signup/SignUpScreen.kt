package com.example.droidchat.ui.feature.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.droidchat.ui.feature.components.area.signup.SignUpScreenArea
import com.example.droidchat.ui.feature.components.dialog.AlertDialogErrorApi
import com.example.droidchat.ui.feature.signup.viewModel.SignUpEvent
import com.example.droidchat.ui.feature.signup.viewModel.SignUpState
import com.example.droidchat.ui.feature.signup.viewModel.SignUpViewModel
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val onFormEvent: (SignUpEvent) -> Unit = remember { { viewModel.onFormEvent(it) } }

    SignUpScreen(
        state = state,
        onFormEvent = onFormEvent
    )

    state.apiErrorMessage?.let {
        AlertDialogErrorApi(
            title = it,
            onEventDismiss = { onFormEvent(SignUpEvent.AlertDialogDismiss) }
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