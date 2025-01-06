package com.example.droidchat.ui.feature.message

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun MessageRoute() {
    MessageScreenScreen()
}

@Composable
private fun MessageScreenScreen() {
    Text("Message Screen")
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DroidChatTheme {
        MessageScreenScreen()
    }
}