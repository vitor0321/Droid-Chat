package com.example.droidchat.ui.feature.signin.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.droidchat.ui.components.area.shared.AppDialogArea
import com.example.droidchat.ui.feature.signin.navigation.SignInAction
import com.example.droidchat.ui.strings.strings
import kotlinx.coroutines.flow.SharedFlow

@Composable
internal fun SignInNavigationEffect(
    actions: SharedFlow<SignInAction>,
    navigateToMessages: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    val context = LocalContext.current
    val showErrorMessage = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        actions.collect { action ->
            when (action) {
                is SignInAction.Success -> navigateToMessages()

                is SignInAction.Error.GenericError ->
                    Toast.makeText(context, strings.errorMessagesStrings.commonGenericErrorMessage, Toast.LENGTH_SHORT).show()

                is SignInAction.Error.UnauthorizedError ->
                    showErrorMessage.value = true

                is SignInAction.NavigateToSignUp -> navigateToSignUp()
            }
        }
    }

    if (showErrorMessage.value)
        AppDialogArea(
            title = strings.errorMessagesStrings.commonGenericErrorTitle,
            message = strings.errorMessagesStrings.errorMessageInvalidUsernameOrPassword,
            onCloseDialog = { showErrorMessage.value = false },
        )
}