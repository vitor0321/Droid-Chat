package com.example.droidchat.ui.feature.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun AlertDialogErrorApi(
    title: String,
    onEventDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onEventDismiss,
        title = {
            TextButton(
                onClick = onEventDismiss,
                content = { Text(text = strings.errorMessagesStrings.commonGenericErrorTitle) }
            )
        },
        text = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        confirmButton = {
            TextButton(
                onClick = onEventDismiss,
                content = { Text(text = strings.commonStrings.commonOk) }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurface
    )
}

@Preview(showBackground = true)
@Composable
private fun AlertDialogErrorApiPreview() {
    DroidChatTheme {
        AlertDialogErrorApi(
            title = "Erro de validação de formulário, confira os dados e tente novamente",
            onEventDismiss = { }
        )
    }
}