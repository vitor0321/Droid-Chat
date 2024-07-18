package com.walcker.droidchat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.walcker.droidchat.strings.strings

internal const val SplashRoute = "splash"
internal const val SignInRoute = "signIn"
internal const val SignUpRoute = "signUp"

@Composable
internal fun ChatNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashRoute,
        builder = {
            composable(SplashRoute) {
                Text(
                    text = strings.signInStrings.featureLoginEmail
                )
            }
            composable(SignInRoute) {

            }
            composable(SignUpRoute) {

            }
        }
    )
}