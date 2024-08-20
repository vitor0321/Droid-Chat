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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.droidchat.R
import com.walcker.droidchat.strings.strings
import com.walcker.droidchat.ui.components.PrimaryButton
import com.walcker.droidchat.ui.components.PrimaryTextField
import com.walcker.droidchat.ui.theme.BackgroundGradient
import com.walcker.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.ui.theme.SpaceLarge
import com.walcker.droidchat.ui.theme.SpaceMedium
import com.walcker.droidchat.ui.theme.SpaceXSmall

@Composable
internal fun SignInRoute() {
    SignInScreen()
}

@Composable
internal fun SignInScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .background(brush = BackgroundGradient),
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.padding(SpaceLarge))

            PrimaryTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = strings.signInStrings.featureLoginEmail,
                leadingIcon = R.drawable.ic_envelope,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                errorMessage = null
            )

            Spacer(modifier = Modifier.padding(SpaceXSmall))

            PrimaryTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = strings.signInStrings.featureLoginPassword,
                leadingIcon = R.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                errorMessage = null
            )

            Spacer(modifier = Modifier.padding(SpaceLarge))

            PrimaryButton(
                text = strings.signInStrings.featureLoginButton,
                isLoading = isLoading,
                onClick = { isLoading = !isLoading },
            )
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        SignInScreen()
    }
}