package com.example.droidchat.ui.components.field.shared.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.droidchat.R
import com.example.droidchat.ui.components.field.shared.animated.AnimatedContent
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.DroidSpace

@Composable
internal fun GeneralEmptyList(
    modifier: Modifier = Modifier,
    message: String,
    resource: (@Composable () -> Unit)? = null,
) {

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(DroidSpace.MMedium.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        resource?.let {
            Box(
                modifier = Modifier.sizeIn(
                    maxWidth = DroidSpace.XXExtraLarge.value,
                    maxHeight = DroidSpace.XXExtraLarge.value
                )
            ) {
                it()
            }
            Spacer(modifier = Modifier.size(DroidSpace.MMedium.value))
        }

        Spacer(modifier = Modifier.size(DroidSpace.XSmall.value))

        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GeneralEmptyListPreview() {
    DroidChatTheme {
        GeneralEmptyList(
            message = "No chats available",
            resource = {
                AnimatedContent(
                    modifier = Modifier.size(DroidSpace.XXExtraLarge.value),
                    resId = R.raw.animation_empty_list,
                )
            }
        )
    }
}