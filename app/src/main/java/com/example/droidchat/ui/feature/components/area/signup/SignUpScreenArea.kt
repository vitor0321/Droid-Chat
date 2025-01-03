package com.example.droidchat.ui.feature.components.area.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.feature.components.field.signup.BodySigUpField
import com.example.droidchat.ui.feature.components.field.signup.BottomSheetSigUpField
import com.example.droidchat.ui.feature.components.field.signup.HeadSignUpField
import com.example.droidchat.ui.feature.signup.viewModel.SignUpEvent
import com.example.droidchat.ui.feature.signup.viewModel.SignUpState
import com.example.droidchat.ui.theme.BackgroundGradient
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun SignUpScreenArea(
    state: SignUpState,
    onFormEvent: (SignUpEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState()),
    ) {
        HeadSignUpField(
            bodySignUp = {
                BodySigUpField(
                    state = state,
                    onFormEvent = onFormEvent,
                )
            },
            bottomSheetSigInField = {
                BottomSheetSigUpField(
                    isProfilePictureModalBottomSheetOpen = state.isProfilePictureModalBottomSheetOpen,
                    onFormEvent = onFormEvent,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenAreaPreview() {
    DroidChatTheme {
        SignUpScreenArea(
            state = SignUpState(),
            onFormEvent = {},
        )
    }
}