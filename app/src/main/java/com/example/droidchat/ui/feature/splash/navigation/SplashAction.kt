package com.example.droidchat.ui.feature.splash.navigation

internal interface SplashAction {
    data object NavigateToChats : SplashAction
    data object UserNotAuthenticated : SplashAction
    data object ShowErrorDialog : SplashAction
}