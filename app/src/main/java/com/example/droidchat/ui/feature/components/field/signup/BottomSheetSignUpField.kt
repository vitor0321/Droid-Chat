package com.example.droidchat.ui.feature.components.field.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.feature.components.bottomSheet.ProfilePictureOptionsModalBottomSheet
import com.example.droidchat.ui.feature.signup.viewModel.SignUpEvent
import com.example.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheetSigUpField(
    isProfilePictureModalBottomSheetOpen: Boolean,
    onFormEvent: (SignUpEvent) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (isProfilePictureModalBottomSheetOpen) {
        ProfilePictureOptionsModalBottomSheet(
            onPictureSelected = { uri ->
                onFormEvent(SignUpEvent.ProfilePhotoUriChanged(uri))
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onFormEvent(SignUpEvent.CloseProfilePictureOptionsModalBottomSheet)
                    }
                }
            },
            onDismissRequest = {
                onFormEvent(SignUpEvent.CloseProfilePictureOptionsModalBottomSheet)
            },
            sheetState = sheetState,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomSheetSigInFieldPreview() {
    DroidChatTheme {
        BottomSheetSigUpField(
            isProfilePictureModalBottomSheetOpen = true,
            onFormEvent = {},
        )
    }
}