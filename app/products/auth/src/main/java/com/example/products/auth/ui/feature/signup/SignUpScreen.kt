package com.example.products.auth.ui.feature.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.platform.theme.DroidChatTheme
import com.example.products.auth.ui.components.area.sigup.SignUpScreenArea
import com.example.products.auth.ui.feature.signup.viewModel.SignUpEvent
import com.example.products.auth.ui.feature.signup.viewModel.SignUpFormValidator
import com.example.products.auth.ui.feature.signup.viewModel.SignUpState
import com.example.products.auth.ui.feature.signup.viewModel.SignUpViewModel

@Composable
internal fun SignUpRoute(
    viewModel: SignUpViewModel = viewModel {
        SignUpViewModel(SignUpFormValidator())
    }
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