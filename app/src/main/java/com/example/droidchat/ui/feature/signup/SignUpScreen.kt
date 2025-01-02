package com.example.droidchat.ui.feature.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.droidchat.ui.feature.components.area.sigup.SignUpScreenArea
import com.example.droidchat.ui.feature.signup.viewModel.SignUpEvent
import com.example.droidchat.ui.feature.signup.viewModel.SignUpState
import com.example.droidchat.ui.feature.signup.viewModel.SignUpViewModel
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state = viewModel.state

    SignUpScreen(
        state = state,
        onFormEvent = viewModel::onFormEvent
    )
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