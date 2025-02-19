package com.example.droidchat.ui.components.bottomNavigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.droidchat.ui.navigation.DroidChatNavigationState
import com.example.droidchat.ui.navigation.TopLevelDestination
import com.example.droidchat.ui.navigation.rememberDroidChatNavigationState
import com.example.droidchat.ui.theme.DroidChatTheme
import com.walcker.topaz.ExperimentalTopazComposeLibraryApi
import com.walcker.topaz.components.bottom.TopazBottomNavigationMenu
import com.walcker.topaz.components.bottom.TopazFloatingActionButton
import kotlin.reflect.KClass

@OptIn(ExperimentalTopazComposeLibraryApi::class)
@Composable
internal fun BottomNavigationMenu(
    modifier: Modifier = Modifier,
    navigationState: DroidChatNavigationState,
) {
    TopazBottomNavigationMenu(
        modifier = modifier,
        content = {
            Row {
                navigationState.topLevelDestination.forEach { topLevelDestination ->
                    if (topLevelDestination == TopLevelDestination.PlusButton) {
                        TopazFloatingActionButton(
                            onClick = { navigationState.navigateToTopLevelDestination(topLevelDestination) }
                        )
                    } else {
                        NavigationBarItem(
                            topLevelDestination = topLevelDestination,
                            navigationState = navigationState
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun RowScope.NavigationBarItem(
    topLevelDestination: TopLevelDestination,
    navigationState: DroidChatNavigationState
) {
    val selected = navigationState.currentDestination.isRouteInHierarchy(topLevelDestination.route)
    val label = topLevelDestination.title
    NavigationBarItem(
        selected = selected,
        icon = {
            topLevelDestination.icon?.let {
                Icon(painter = painterResource(it), contentDescription = label)
            }
        },
        label = { label?.let { Text(text = it) } },
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primary,
            selectedIconColor = MaterialTheme.colorScheme.onSecondary,
            selectedTextColor = MaterialTheme.colorScheme.onSecondary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        onClick = { navigationState.navigateToTopLevelDestination(topLevelDestination) }
    )
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } == true

@Preview(showBackground = true)
@Composable
private fun BottomNavigationMenuPreview() {
    DroidChatTheme {
        BottomNavigationMenu(
            navigationState = rememberDroidChatNavigationState()
        )
    }
}