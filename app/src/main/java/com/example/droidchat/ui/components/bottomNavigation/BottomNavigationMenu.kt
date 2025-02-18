package com.example.droidchat.ui.components.bottomNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.droidchat.ui.navigation.DroidChatNavigationState
import com.example.droidchat.ui.navigation.TopLevelDestination
import com.example.droidchat.ui.navigation.rememberDroidChatNavigationState
import com.example.droidchat.ui.theme.DroidChatTheme
import com.example.droidchat.ui.theme.Grey1
import kotlin.reflect.KClass

@Composable
internal fun BottomNavigationMenu(
    modifier: Modifier = Modifier,
    navigationState: DroidChatNavigationState,
) {
    Column {
        HorizontalDivider(color = Grey1)

        NavigationBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp,
        ) {
            navigationState.topLevelDestination.forEach { topLevelDestination ->
                if (topLevelDestination == TopLevelDestination.PlusButton) {
                    FloatingActionButton(
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

@Composable
private fun FloatingActionButton(
    onClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = Modifier.padding(top = 6.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp,
        ),
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
        )
    }
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