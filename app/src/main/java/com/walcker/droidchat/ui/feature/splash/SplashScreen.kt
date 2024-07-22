package com.walcker.droidchat.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.droidchat.R
import com.walcker.droidchat.strings.strings
import com.walcker.droidchat.ui.theme.BackgroundGradient
import com.walcker.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
internal fun SplashRoute(
    onNavigationToSignIn: () -> Unit,
) {
    SplashScreen()
    LaunchedEffect(Unit) {
        delay(2000)
        onNavigationToSignIn()
    }
}

@Composable
private fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
        )

        Spacer(modifier = Modifier.height(77.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_safety),
                contentDescription = "Lock",
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = strings.splashStrings.splashSafetyInfo,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = Typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    DroidChatTheme {
        SplashScreen()
    }
}