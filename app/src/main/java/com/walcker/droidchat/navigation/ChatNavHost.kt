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
import com.walcker.droidchat.ui.feature.singup.SignUpRoute
import com.walcker.droidchat.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

internal sealed interface Route {
    @Serializable
    object SplashScreenRoute

    @Serializable
    object SignInScreenRoute

    @Serializable
    object SignUpScreenRoute
}

@Composable
internal fun ChatNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.SplashScreenRoute,
        enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Up) },
        exitTransition = { this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Down) },
        builder = {
            composable<Route.SplashScreenRoute> {
                SplashRoute(
                    onNavigationToSignIn = {
                        navController.navigate(
                            route = Route.SignInScreenRoute,
                            navOptions = navOptions {
                                popUpTo<Route.SplashScreenRoute> {
                                    inclusive = true
                                }
                            })

                    }
                )
            }
            composable<Route.SignInScreenRoute>(
                enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right) },
                exitTransition = { this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left) },
            ) {
                SignInRoute(
                    navigateToSignUp = {
                        navController.navigate(Route.SignUpScreenRoute)
                    }
                )
            }
            composable<Route.SignUpScreenRoute>(
                enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left) },
                exitTransition = { this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Right) }
            ) {
                SignUpRoute()
            }
        }
    )
}