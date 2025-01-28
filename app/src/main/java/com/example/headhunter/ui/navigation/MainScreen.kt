package com.example.headhunter.ui.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import com.example.headhunter.ui.pages.FavoritePage
import com.example.headhunter.ui.pages.MessagePage
import com.example.headhunter.ui.pages.ProfilePage
import com.example.headhunter.ui.pages.ResponsePage
import com.example.headhunter.ui.pages.SearchPage

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.navigationBarsPadding()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.SearchPage.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(BottomNavItem.SearchPage.route) { SearchPage() }
            composable(BottomNavItem.FavoritePage.route) { FavoritePage() }
            composable(BottomNavItem.ResponsePage.route) { ResponsePage() }
            composable(BottomNavItem.MessagePage.route) { MessagePage() }
            composable(BottomNavItem.ProfilePage.route) { ProfilePage() }
        }
    }
}