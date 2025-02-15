package com.example.droidchat.ui.components.field.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.field.animated.AnimatedContent
import com.example.droidchat.ui.components.field.shared.PrimaryButton
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace

@Composable
internal fun ErrorAnimated(
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit,
) {

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(DroidSpace.MMedium.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.weight(1f))

        AnimatedContent(modifier = Modifier.size(DroidSpace.XXExtraLarge.value))

        Spacer(modifier = Modifier.size(DroidSpace.MMedium.value))

        Text(
            text = strings.errorMessagesStrings.commonGenericErrorTitle,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.size(DroidSpace.XSmall.value))

        Text(
            text = strings.errorMessagesStrings.commonGenericErrorMessage,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.height(DroidSpace.XLarge.value),
            text = strings.errorMessagesStrings.commonTryAgain,
            onClick = onTryAgain,
        )

        Spacer(modifier = Modifier.size(DroidSpace.MMedium.value))
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorAnimatedPreview() {
    DroidChatTheme {
        ErrorAnimated(
            onTryAgain = { }
        )
    }
}