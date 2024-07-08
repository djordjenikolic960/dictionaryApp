package com.example.dictionaryapp.features.navigation.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        NavigationBarItem(
            selected = selectedTabIndex == 0,
            onClick = {
                selectedTabIndex = 0
                navController.navigate("dictionary") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = "Dictionary") },
            label = { Text("Dictionary") }
        )
        NavigationBarItem(
            selected = selectedTabIndex == 1,
            onClick = {
                selectedTabIndex = 1
                navController.navigate("history") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "History") },
            label = { Text("History") }
        )
    }
}