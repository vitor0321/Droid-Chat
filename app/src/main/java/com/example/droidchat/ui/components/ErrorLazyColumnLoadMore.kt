package com.example.droidchat.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@Composable
internal fun ErrorLazyColumnLoadMore(
    description: String = strings.commonStrings.commonErrorLoadingMore,
    onErrorLoadMore: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(TopazSpacerSizeToken.MMedium.horizontalHeight)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(TopazSpacerSizeToken.XSmall.horizontalHeight),
            text = description,
            color = MaterialTheme.colorScheme.error,
        )

        PrimaryButton(
            modifier = Modifier
                .padding(horizontal = TopazSpacerSizeToken.MLarge.horizontalHeight)
                .height(TopazSpacerSizeToken.XSLarge.horizontalHeight),
            text = strings.errorMessagesStrings.commonTryAgain,
            onClick = onErrorLoadMore,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorLazyColumnLoadMorePreview() {
    DroidChatTheme {
        ErrorLazyColumnLoadMore(
            description = "Error ao carregar mais",
            onErrorLoadMore = { }
        )
    }
}