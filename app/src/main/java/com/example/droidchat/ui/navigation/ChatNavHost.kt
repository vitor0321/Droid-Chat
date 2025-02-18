package com.example.droidchat.ui.navigation

import android.app.Activity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.droidchat.ui.extension.slidOutTo
import com.example.droidchat.ui.extension.slideInTo
import com.example.droidchat.ui.feature.chats.ChatsRoute
import com.example.droidchat.ui.feature.chats.navigation.navigateToChats
import com.example.droidchat.ui.feature.signin.SignInRoute
import com.example.droidchat.ui.feature.signup.SignUpRoute
import com.example.droidchat.ui.feature.splash.SplashRoute
import com.example.droidchat.ui.feature.users.UsersRoute
import com.example.droidchat.ui.navigation.Route.SplashRoute

@Composable
internal fun ChatNavHost(
    navigationState: DroidChatNavigationState,
) {
    val navController = navigationState.navController
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
                onNavigateToChats = {
                    navController.navigateToChats(
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
                    navController.navigateToChats(
                        navOptions = navOptions { popUpTo(SplashRoute) { inclusive = true } }
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

        composable<Route.ChatsRoute> {
            ChatsRoute()
        }

        composable<Route.UsersRoute> {
            UsersRoute()
        }
    }
}