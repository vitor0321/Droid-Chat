package com.example.platform.extension

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

public fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInTo(
    direction: AnimatedContentTransitionScope.SlideDirection
): EnterTransition {
    return this.slideIntoContainer(
        towards = direction,
        animationSpec = tween(durationMillis = 300)
    )
}

public fun AnimatedContentTransitionScope<NavBackStackEntry>.slidOutTo(
    direction: AnimatedContentTransitionScope.SlideDirection
): ExitTransition {
    return this.slideOutOfContainer(
        towards = direction,
        animationSpec = tween(durationMillis = 300)
    )
}