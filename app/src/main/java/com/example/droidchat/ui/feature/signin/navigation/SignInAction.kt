package com.example.droidchat.ui.feature.signin.navigation

internal sealed interface SignInAction {
    data object Success : SignInAction
    data object NavigateToSignUp : SignInAction
    sealed interface Error : SignInAction {
        data object GenericError : Error
        data object UnauthorizedError : Error
    }
}