package com.example.products.auth.ui.components.field.sigup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.platform.theme.DroidChatTheme
import com.example.products.auth.ui.components.bottomSheet.ProfilePictureOptionsModalBottomSheet
import com.example.products.auth.ui.feature.signup.viewModel.SignUpEvent
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