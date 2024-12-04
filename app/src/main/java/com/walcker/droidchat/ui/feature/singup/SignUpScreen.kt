package com.walcker.droidchat.ui.feature.singup

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.walcker.droidchat.R
import com.walcker.droidchat.strings.strings
import com.walcker.droidchat.ui.components.PrimaryButton
import com.walcker.droidchat.ui.components.ProfilePictureOptionsModalBottomSheet
import com.walcker.droidchat.ui.components.ProfilePictureSelector
import com.walcker.droidchat.ui.components.SecondaryTextField
import com.walcker.droidchat.ui.theme.BackgroundGradient
import com.walcker.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.ui.theme.DroidSpace

@Composable
internal fun SignUpRoute() {
    SignUpScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SignUpScreen() {
    val profilePictureSelectedUri = remember { mutableStateOf<Uri?>(null) }
    val openProfilePictureOptionsModalBottomSheet = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(DroidSpace.XLarge.value))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(DroidSpace.MMedium.value))

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(DroidSpace.None.value),
                    bottomEnd = CornerSize(DroidSpace.None.value)
                ),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(DroidSpace.MMedium.value)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePictureSelector(
                        modifier = Modifier.clickable {
                            openProfilePictureOptionsModalBottomSheet.value = true
                        },
                        imageUri = profilePictureSelectedUri.value
                    )

                    Spacer(modifier = Modifier.height(DroidSpace.MLarge.value))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpFirstName,
                        value = "",
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpLastName,
                        value = "",
                        onValueChange = {}
                    )

                    Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpEmail,
                        value = "",
                        onValueChange = {},
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpPassword,
                        value = "",
                        onValueChange = {},
                        keyboardType = KeyboardType.Password,
                    )

                    Spacer(modifier = Modifier.height(DroidSpace.XMedium.value))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpPasswordConfirmation,
                        value = "",
                        onValueChange = {},
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    PrimaryButton(
                        modifier = Modifier
                            .padding(vertical = DroidSpace.MLarge.value)
                            .fillMaxWidth(),
                        text = strings.signUpStrings.featureSignUpButton,
                        onClick = {}
                    )
                }
            }
            if (openProfilePictureOptionsModalBottomSheet.value) {
                ProfilePictureOptionsModalBottomSheet(
                    onDismissRequest = {
                        openProfilePictureOptionsModalBottomSheet.value = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUp() {
    DroidChatTheme {
        SignUpScreen()
    }
}