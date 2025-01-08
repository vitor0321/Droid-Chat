package com.example.droidchat.ui.components.area.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.dialog.AppDialog
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun AppDialogArea(
    title: String? = null,
    message: String,
    confirmButtonText: String = strings.commonStrings.commonOk,
    onCloseDialog: () -> Unit,
    onEventDismiss: () -> Unit = { onCloseDialog() },
) {
    AppDialog(
        title = title,
        message = message,
        confirmButtonText = confirmButtonText,
        onEventDismiss = { onEventDismiss() },
        onEventConfirm = { onCloseDialog() },
    )
}

@Preview(showBackground = true)
@Composable
private fun AppDialogAreaPreview() {
    DroidChatTheme {
        AppDialogArea(
            message = "This is a dialog",
            confirmButtonText = "Ok",
            onCloseDialog = { }
        )
    }
}