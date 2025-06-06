package com.example.droidchat.ui.components.field.shared.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.field.shared.PrimaryButton
import com.example.droidchat.ui.components.field.shared.animated.AnimatedContent
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpaceToken

@Composable
internal fun GeneralError(
    modifier: Modifier = Modifier,
    title: String = strings.errorMessagesStrings.commonGenericErrorMessage,
    message: String = strings.errorMessagesStrings.commonServiceUnavailable,
    resource: (@Composable () -> Unit)? = {
        AnimatedContent(modifier = Modifier.size(DroidSpaceToken.XXExtraLarge.value))
    },
    onClickButton: () -> Unit = {},
    button: (@Composable () -> Unit)? = {
        PrimaryButton(
            modifier = Modifier.height(DroidSpaceToken.XLarge.value),
            text = strings.errorMessagesStrings.commonTryAgain,
            onClick = onClickButton,
        )
    },
) {

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(DroidSpaceToken.MMedium.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        resource?.let {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.sizeIn(
                    maxWidth = DroidSpaceToken.XXExtraLarge.value,
                    maxHeight = DroidSpaceToken.XXExtraLarge.value
                )
            ) {
                it()
            }
            Spacer(modifier = Modifier.size(DroidSpaceToken.MMedium.value))
        }

        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.size(DroidSpaceToken.XSmall.value))

        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
        )

        button?.let {
            Spacer(modifier = Modifier.weight(1f))
            it()
            Spacer(modifier = Modifier.size(DroidSpaceToken.MMedium.value))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GeneralErrorPreview() {
    DroidChatTheme {
        GeneralError(
            title = "Error",
            message = "Something went wrong",
        )
    }
}