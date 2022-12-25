package com.example.aorgia.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Man
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.aorgia.app.navigation.Screen
import com.example.aorgia.components.image.UserIconPreview
import com.example.aorgia.data.local.LocalUser
import com.example.aorgia.ui.theme.KittyKitty
import com.example.aorgia.ui.theme.LightDirtyGray

private sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object BottomHome : BottomNavScreens(Screen.Home.route, "Домашняя", Icons.Default.Home)
    object BottomProfile : BottomNavScreens(Screen.Profile.route, "", Icons.Default.Man)
}

//TODO: ADD NAVCONTROLLER AND ANIM NAVIGATION
@Composable
fun BottomNavigationPanel(
    userData: LocalUser.Data,
    selectedScreen: String,
    changeScreen: (screen: String) -> Unit
) {
    val bottomScreens = listOf(
        BottomNavScreens.BottomHome,
        BottomNavScreens.BottomProfile
    )

    NavigationBar(
        containerColor = KittyKitty,
        contentColor = LightDirtyGray
    ) {

        bottomScreens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen.route) {
                        Screen.Profile.route -> {
                            UserIconPreview(size = 24.dp, imageLink = userData.icon.value)
                        }
                        else -> {
                            Image(imageVector = screen.icon, contentDescription = "icon")
                        }
                    }
                },
                selected = selectedScreen == screen.route,
                onClick = {
                      changeScreen(screen.route)
                },
                label = {
                    when (screen.route) {
                        Screen.Profile.route -> {
                            Text(text = userData.tag.value)
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