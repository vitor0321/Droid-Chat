package com.example.droidchat.ui.feature.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.theme.DroidChatTheme

@Composable
internal fun HomeRoute() {
    HomeScreenScreen()
}

@Composable
private fun HomeScreenScreen() {
    Text("Message Screen")
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DroidChatTheme {
        HomeScreenScreen()
    }
}