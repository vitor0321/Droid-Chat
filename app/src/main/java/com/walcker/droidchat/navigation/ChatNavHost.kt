package com.walcker.droidchat.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.walcker.droidchat.navigation.extension.slideInTo
import com.walcker.droidchat.navigation.extension.slideOutTo
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
        enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Up) },
        exitTransition = { this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Down) },
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
            composable<SignInScreenRoute>(
                enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right) },
                exitTransition = { this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left) },
            ) {
                SignInRoute(
                    navigateToSignUp = {
                        navController.navigate(SignUpScreenRoute)
                    }
                )
            }
            composable<SignUpScreenRoute>(
                enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left) },
                exitTransition = { this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left) }
            ) {

            }
        }
    )
}