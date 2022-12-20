package com.example.aorgia.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Man
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.image.UserIconPreview
import com.example.aorgia.data.local.LocalUserInfo
import com.example.aorgia.ui.theme.Darkness
import com.example.aorgia.ui.theme.KittyKitty
import com.example.aorgia.ui.theme.LightDirtyGray
import kotlinx.coroutines.launch

private sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object BottomHome : BottomNavScreens(Screen.Home.route, "Домашняя", Icons.Default.Home)
    object BottomProfile : BottomNavScreens(Screen.Profile.route, "", Icons.Default.Man)
}

@Composable
fun BottomNavigationPanel(
    navController: NavController,
    userData: LocalUserInfo
) {
    val bottomScreens = listOf(
        BottomNavScreens.BottomHome,
        BottomNavScreens.BottomProfile
    )

    NavigationBar(
        containerColor = KittyKitty,
        contentColor = LightDirtyGray
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomScreens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen.route) {
                        Screen.Profile.route -> {
                            UserIconPreview(size = 24.dp, imageLink = userData.icon)
                        }
                        else -> {
                            Image(imageVector = screen.icon, contentDescription = "icon")
                        }
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                inclusive = true
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = {
                    when (screen.route) {
                        Screen.Profile.route -> {
                            Text(text = userData.name)
                        } else -> {
                            Text(text = screen.title)
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = LightDirtyGray,
                    unselectedTextColor = LightDirtyGray,
                    indicatorColor = Color.White
                )
            )
        }
    }
}

@Composable
private fun BottomTextTitle(title: String) {
    Text(
        text = title,
        color = LightDirtyGray,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center
    )
}