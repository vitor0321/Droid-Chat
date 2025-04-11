package com.example.droidchat.ui.feature.splash.components.area

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.R
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.BackgroundGradient
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@Composable
internal fun SplashScreenArea() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradient)
            .padding(TopazSpacerSizeToken.MMedium.all),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(TopazSpacerSizeToken.XExtraLarge.verticalWidth))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_safety),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(TopazSpacerSizeToken.XSmall.horizontalHeight))

            Text(
                text = strings.splashStrings.splashSafetyInfo,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenAreaPreview() {
    DroidChatTheme {
        SplashScreenArea()
    }
}