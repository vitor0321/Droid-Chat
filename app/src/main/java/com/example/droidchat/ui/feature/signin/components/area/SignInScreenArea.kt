package com.example.droidchat.ui.feature.signin.components.area

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
import com.example.droidchat.ui.feature.signin.components.field.BodySigInField
import com.example.droidchat.ui.feature.signin.components.field.BottomSigInField
import com.example.droidchat.ui.feature.signin.viewModel.SignInEvent
import com.example.droidchat.ui.feature.signin.viewModel.SignInState
import com.example.droidchat.ui.theme.BackgroundGradient
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun SigInScreenArea(
    state: SignInState,
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
            onSinUp = { onFormEvent(SignInEvent.OnSignUp) }
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
        )
    }
}