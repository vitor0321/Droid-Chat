package com.example.droidchat.ui.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun AppDialog(
    title: String? = null,
    message: String,
    confirmButtonText: String,
    onEventDismiss: () -> Unit,
    onEventConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onEventDismiss,
        title = {
            TextButton(
                onClick = onEventDismiss,
                content = { title?.let { Text(text = title) } }
            )
        },
        text = {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(
                onClick = onEventConfirm,
                content = { Text(text = confirmButtonText) }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSecondary,
        textContentColor = MaterialTheme.colorScheme.onSecondary
    )
}

@Preview(showBackground = true)
@Composable
private fun AlertDialogErrorApiPreview() {
    DroidChatTheme {
        AppDialog(
            title = "Erro de validação de formulário",
            message = "Erro de validação de formulário, confira os dados e tente novamente",
            confirmButtonText = "Ok",
            onEventDismiss = { },
            onEventConfirm = { },
        )
    }
}