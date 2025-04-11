package com.example.droidchat.ui.feature.chatDetails.components.area

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.ui.components.ChatMessageTextField
import com.example.droidchat.ui.strings.strings
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.image.TopazRoundedAvatar
import com.walcker.topaz.components.scaffold.TopazScaffold
import com.walcker.topaz.components.topAppBar.TopazTopAppBar
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@OptIn(ExperimentalTopazComposeLibraryApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun ChatDetailsArea(
    messageText: String,
    onNavigationBack: () -> Unit,
    onMessageChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
) {
    TopazScaffold(
        topBar = {
            TopazTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TopazRoundedAvatar(
                            imageUri = null,
                            contentDescription = "User Avatar",
                            size = TopazSpacerSizeToken.XSLarge,
                            modifier = Modifier
                        )
                        Column(
                            modifier = Modifier.padding(TopazSpacerSizeToken.MMedium.verticalWidth),
                        ) {
                            Text(
                                text = "John Doe",
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "Online",
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                style = MaterialTheme.typography.labelMedium,
                            )
                        }
                    }
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            onNavigationBack()
                        },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            )
        },
    ) {
        Column {
            Box {

            }
            ChatMessageTextField(
                modifier = Modifier
                    .padding(horizontal = TopazSpacerSizeToken.MMedium.all)
                    .padding(
                        bottom = TopazSpacerSizeToken.MMedium.all,
                        top = TopazSpacerSizeToken.XSmall.all
                    ),
                value = messageText,
                placeholder = strings.chatDetailsStrings.featureChatDetailTextFieldPlaceholder,
                onSendClicked = onSendClicked,
                onInputChange = onMessageChanged,
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ChatDetailsAreaPreview() {
    DroidChatTheme {
        ChatDetailsArea(
            messageText = "",
            onNavigationBack = { },
            onMessageChanged = { },
            onSendClicked = { },
        )
    }
}