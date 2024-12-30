package com.example.droidchat.ui.components.area.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.ui.components.field.signin.BodySigInField
import com.example.droidchat.ui.components.field.signin.BottomSigInField
import com.example.droidchat.ui.feature.signin.viewModel.SignInEvent
import com.example.droidchat.ui.feature.signin.viewModel.SignInState
import com.example.platform.theme.BackgroundGradient
import com.example.droidchat.DroidChatTheme

@Composable
internal fun SigInScreenArea(
    state: SignInState,
    onRegisterClick: () -> Unit,
    onFormEvent: (SignInEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BodySigInField(
            email = state.email,
            emailError = state.emailError,
            password = state.password,
            passwordError = state.passwordError,
            isLoading = state.isLoading,
            onFormEvent = onFormEvent
        )

        Spacer(modifier = Modifier.height(56.dp))

        BottomSigInField(
            onRegisterClick = onRegisterClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SigInScreenAreaPreview() {
    DroidChatTheme {
        SigInScreenArea(
            state = SignInState(),
            onFormEvent = {},
            onRegisterClick = {}
        )
    }
}