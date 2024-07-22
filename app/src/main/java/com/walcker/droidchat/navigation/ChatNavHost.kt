package com.walcker.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.walcker.droidchat.ui.feature.signin.SignInRoute
import com.walcker.droidchat.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

@Serializable
object SplashScreenRoute

@Serializable
object SignInScreenRoute

@Serializable
object SignUpScreenRoute

@Composable
internal fun ChatNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreenRoute,
        builder = {
            composable<SplashScreenRoute> {
                SplashRoute(
                    onNavigationToSignIn = {
                        navController.navigate(
                            route = SignInScreenRoute,
                            navOptions = navOptions {
                                popUpTo<SplashScreenRoute> {
                                    inclusive = true
                                }
                            })

                    }
                )
            }
            composable<SignInScreenRoute> {
                SignInRoute()
            }
            composable<SignUpScreenRoute> {

            }
        }
    )
}