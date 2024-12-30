package com.example.droidchat.ui.components.field.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.DroidChatTheme
import com.example.droidchat.strings.strings

@Composable
internal fun BottomSigInField(
    onRegisterClick: () -> Unit,
) {
    val noAccountText = remember { strings.signInStrings.featureLoginNoAccount }
    val registerText = remember { strings.signInStrings.featureLoginRegister }
    val noAccountRegisterText = remember { "$noAccountText $registerText" }
    val registerTextStartIndex = remember { noAccountRegisterText.indexOf(registerText) }
    val registerTextEndIndex = remember { registerTextStartIndex + registerText.length }
    Column {
        val annotatedString = buildAnnotatedString {
            append(noAccountRegisterText)

            addStyle(
                style = SpanStyle(
                    color = Color.White
                ),
                start = 0,
                end = registerTextStartIndex,
            )

            addLink(
                clickable = LinkAnnotation.Clickable(
                    tag = "register_text,",
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline,
                        )
                    ),
                    linkInteractionListener = {
                        onRegisterClick()
                    }
                ),
                start = registerTextStartIndex,
                end = registerTextEndIndex,
            )
        }

        Text(text = annotatedString)
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomSigInFieldPreview() {
    DroidChatTheme {
        BottomSigInField(
            onRegisterClick = {}
        )
    }
}