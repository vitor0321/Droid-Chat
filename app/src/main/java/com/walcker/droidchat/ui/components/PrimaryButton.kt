package com.walcker.droidchat.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.walcker.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.ui.theme.DroidSpace

@Composable
fun PrimaryButton(
    text: String,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier
            .height(DroidSpace.ExtraLarge.value)
            .imePadding(),
        enabled = !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
        ),
        onClick = onClick,
        content = {
            Box(
                modifier = Modifier.animateContentSize()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(DroidSpace.XLarge.value)
                            .aspectRatio(1f),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeCap = StrokeCap.Round,
                    )
                } else {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = text,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonPreview() {
    DroidChatTheme {
        PrimaryButton(
            text = "Sign in",
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonLoadingPreview() {
    DroidChatTheme {
        PrimaryButton(
            text = "Sign in",
            isLoading = true,
            onClick = {},
        )
    }
}