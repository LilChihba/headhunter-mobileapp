package com.example.headhunter.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.headhunter.R
import com.example.headhunter.ui.theme.Blue
import com.example.headhunter.ui.theme.Grey4
import com.example.headhunter.ui.theme.Shadows


sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, val label: String, ) {
    object SearchPage : BottomNavItem("searchPage", R.drawable.search, "Поиск")
    object FavoritePage : BottomNavItem("favoritePage", R.drawable.favorite, "Избранное")
    object ResponsePage : BottomNavItem("responsePage", R.drawable.response, "Отклики")
    object MessagePage : BottomNavItem("messagePage", R.drawable.message, "Сообщения")
    object ProfilePage : BottomNavItem("profilePage", R.drawable.profile, "Профиль")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.SearchPage,
        BottomNavItem.FavoritePage,
        BottomNavItem.ResponsePage,
        BottomNavItem.MessagePage,
        BottomNavItem.ProfilePage,
    )

    BottomNavigation(
        backgroundColor = Shadows,
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(
                    painter = painterResource(item.icon),
                    contentDescription = item.label,
                    modifier = Modifier.size(24.dp)
                ) },
                label = { Text(
                    text = item.label,
                    fontSize = 10.sp
                ) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                selectedContentColor = Blue,
                unselectedContentColor = Grey4,
            )
        }
    }
}