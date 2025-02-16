package com.example.droidchat.ui.navigation

import android.app.Activity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.droidchat.ui.extension.slidOutTo
import com.example.droidchat.ui.extension.slideInTo
import com.example.droidchat.ui.feature.home.HomeRoute
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
    object HomeRoute
}

@Composable
internal fun ChatNavHost() {
    val navController = rememberNavController()
    val activity = LocalContext.current as? Activity

    NavHost(navController = navController, startDestination = SplashRoute) {
        composable<SplashRoute> {
            SplashRoute(
                onNavigateToSignIn = {
                    navController.navigate(
                        route = Route.SignInRoute,
                        navOptions = navOptions { popUpTo(SplashRoute) { inclusive = true } }
                    )
                },
                onNavigateToHome = {
                    navController.navigate(
                        route = Route.HomeRoute,
                        navOptions = navOptions { popUpTo(SplashRoute) { inclusive = true } }
                    )
                },
                onCloseApp = { activity?.finish() },
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
                        route = Route.HomeRoute,
                        navOptions = navOptions { popUpTo(Route.SignInRoute) { inclusive = true } }
                    )
                }
            )
        }

        composable<Route.SignUpRoute>(
            enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left) },
            exitTransition = { this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
            SignUpRoute(onSignInSuccess = { navController.popBackStack() })
        }

        composable<Route.HomeRoute>(
            enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left) },
            exitTransition = { this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
            HomeRoute()
        }
    }
}