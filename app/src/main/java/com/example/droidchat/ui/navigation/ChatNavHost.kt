package com.example.droidchat.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.droidchat.ui.extension.slidOutTo
import com.example.droidchat.ui.extension.slideInTo
import com.example.droidchat.ui.feature.messages.MessageRoute
import com.example.droidchat.ui.feature.signin.SignInRoute
import com.example.droidchat.ui.feature.signup.SignUpRoute
import com.example.droidchat.ui.feature.splash.SplashRoute
import com.example.droidchat.ui.navigation.Route.SplashRoute
import kotlinx.serialization.Serializable

internal sealed interface Route {
    @Serializable
    object SplashRoute

    @Serializable
    object SignInRoute

    @Serializable
    object SignUpRoute

    @Serializable
    object MessageRoute
}

@Composable
internal fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashRoute) {
        composable<SplashRoute> {
            SplashRoute(
                onNavigateToSignIn = {
                    navController.navigate(
                        route = Route.SignInRoute,
                        navOptions = navOptions {
                            popUpTo(SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }

        composable<Route.SignInRoute>(
            enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right) },
            exitTransition = { this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Left) }
        ) {
            SignInRoute(
                navigateToSignUp = {
                    navController.navigate(Route.SignUpRoute)
                },
                navigateToMessages = {
                    navController.navigate(
                        route = Route.MessageRoute,
                        navOptions = navOptions {
                            popUpTo(Route.SignInRoute) {
                                inclusive = true
                            }
                        })
                }
            )
        }

        composable<Route.SignUpRoute>(
            enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left) },
            exitTransition = { this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
            SignUpRoute(onSignUpSuccess = { navController.popBackStack() })
        }

        composable<Route.MessageRoute>(
            enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left) },
            exitTransition = { this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
            MessageRoute()
        }
    }
}