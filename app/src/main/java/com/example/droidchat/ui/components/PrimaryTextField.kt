package com.example.droidchat.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.droidchat.R
import com.example.droidchat.ui.extension.getVisualTransformationForPassword
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.tokens.TopazSpacerSizeToken

@Composable
internal fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    @DrawableRes
    leadingIcon: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    errorMessage: String? = null,
) {

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholder)
            },
            leadingIcon = {
                leadingIcon?.let {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            trailingIcon = {
                if (keyboardType == KeyboardType.Password && value.isNotEmpty()) {
                    val visibilityIcon = if (passwordVisible) R.drawable.ic_visibility
                    else R.drawable.ic_visibility_off

                    Icon(
                        painter = painterResource(id = visibilityIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                passwordVisible = !passwordVisible
                            },
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            visualTransformation = keyboardType.getVisualTransformationForPassword(passwordVisible),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            singleLine = true,
            shape = CircleShape,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = if (errorMessage != null) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onSurfaceVariant,
            )
        )

        errorMessage?.let {
            Text(
                text = it,
                modifier = Modifier.padding(start = TopazSpacerSizeToken.Medium.horizontalHeight),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryTextFieldPreview() {
    DroidChatTheme {
        PrimaryTextField(
            value = "",
            onValueChange = {},
            placeholder = "E-mail",
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrimaryTextFieldErrorPreview() {
    DroidChatTheme {
        PrimaryTextField(
            value = "",
            onValueChange = {},
            placeholder = "E-mail",
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
            errorMessage = "E-mail inválido",
        )
    }
}