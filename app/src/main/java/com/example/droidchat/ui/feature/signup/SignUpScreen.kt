package com.example.droidchat.ui.feature.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.droidchat.R
import com.example.droidchat.ui.components.PrimaryButton
import com.example.droidchat.ui.components.ProfilePictureOptionsModalBottomSheet
import com.example.droidchat.ui.components.ProfilePictureSelector
import com.example.droidchat.ui.components.SecondaryTextField
import com.example.droidchat.ui.feature.signup.viewModel.SignUpFormEvent
import com.example.droidchat.ui.feature.signup.viewModel.SignUpFormState
import com.example.droidchat.ui.feature.signup.viewModel.SignUpFormValidator
import com.example.droidchat.ui.feature.signup.viewModel.SignUpViewModel
import com.example.droidchat.ui.theme.BackgroundGradient
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.droidchat.strings.strings
import kotlinx.coroutines.launch

@Composable
internal fun SignUpRoute(
    viewModel: SignUpViewModel = viewModel {
        SignUpViewModel(SignUpFormValidator())
    }
) {
    val formState = viewModel.formState

    SignUpScreen(
        formState = formState,
        onFormEvent = viewModel::onFormEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreen(
    formState: SignUpFormState,
    onFormEvent: (SignUpFormEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(brush = BackgroundGradient)
            .verticalScroll(state = rememberScrollState()),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp),
                ),
                color = MaterialTheme.colorScheme.surface,
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ProfilePictureSelector(
                        imageUri = formState.profilePictureUri,
                        modifier = Modifier
                            .clickable {
                                onFormEvent(SignUpFormEvent.OpenProfilePictureOptionsModalBottomSheet)
                            }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpFirstName,
                        value = formState.firstName,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.FirstNameChanged(it))
                        },
                        errorText = formState.firstNameError,
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpLastName,
                        value = formState.lastName,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.LastNameChanged(it))
                        },
                        errorText = formState.lastNameError,
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpEmail,
                        value = formState.email,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.EmailChanged(it))
                        },
                        keyboardType = KeyboardType.Email,
                        errorText = formState.emailError,
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpPassword,
                        value = formState.password,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.PasswordChanged(it))
                        },
                        keyboardType = KeyboardType.Password,
                        extraText = formState.passwordExtraText,
                        errorText = formState.passwordError,
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    SecondaryTextField(
                        label = strings.signUpStrings.featureSignUpPasswordConfirmation,
                        value = formState.passwordConfirmation,
                        onValueChange = {
                            onFormEvent(SignUpFormEvent.PasswordConfirmationChanged(it))
                        },
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        extraText = formState.passwordExtraText,
                        errorText = formState.passwordConfirmationError,
                    )

                    Spacer(modifier = Modifier.height(36.dp))

                    PrimaryButton(
                        text = strings.signUpStrings.featureSignUpButton,
                        onClick = { onFormEvent(SignUpFormEvent.Submit) }
                    )
                }
            }

            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            if (formState.isProfilePictureModalBottomSheetOpen) {
                ProfilePictureOptionsModalBottomSheet(
                    onPictureSelected = { uri ->
                        onFormEvent(SignUpFormEvent.ProfilePhotoUriChanged(uri))
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onFormEvent(SignUpFormEvent.CloseProfilePictureOptionsModalBottomSheet)
                            }
                        }
                    },
                    onDismissRequest = {
                        onFormEvent(SignUpFormEvent.CloseProfilePictureOptionsModalBottomSheet)
                    },
                    sheetState = sheetState,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DroidChatTheme {
        SignUpScreen(
            formState = SignUpFormState(),
            onFormEvent = {},
        )
    }
}