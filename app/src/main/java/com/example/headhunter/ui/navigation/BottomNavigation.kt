package com.example.headhunter.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.headhunter.R
import com.example.headhunter.models.Vacancy
import com.example.headhunter.ui.theme.Blue
import com.example.headhunter.ui.theme.Grey4
import com.example.headhunter.ui.theme.Red
import com.example.headhunter.ui.theme.Shadows
import com.example.headhunter.ui.theme.White
import com.example.headhunter.ui.theme.button1
import com.example.headhunter.ui.theme.number
import com.example.headhunter.ui.theme.tab
import com.example.headhunter.ui.theme.text1


sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, val label: String, ) {
    object SearchPage : BottomNavItem("searchPage", R.drawable.search, "Поиск")
    object FavoritePage : BottomNavItem("favoritePage", R.drawable.favorite, "Избранное")
    object ResponsePage : BottomNavItem("responsePage", R.drawable.response, "Отклики")
    object MessagePage : BottomNavItem("messagePage", R.drawable.message, "Сообщения")
    object ProfilePage : BottomNavItem("profilePage", R.drawable.profile, "Профиль")
}

@Composable
fun BottomNavigationBar(navController: NavController, vacancies: List<Vacancy>?) {
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
        val countVacancy = vacancies?.count { it.isFavorite }
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Box{
                        if(item.label == "Избранное" && countVacancy != 0) {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.label,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .size(24.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .padding(start = 20.dp)
                                    .size(13.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Transparent, CircleShape)
                                    .background(Red)
                            ) {
                                Text(
                                    text = "$countVacancy",
                                    style = MaterialTheme.typography.number,
                                    color = White,
                                    modifier = Modifier
                                        .padding(start = 5.dp, top = 1.dp)
                                )
                            }
                        } else {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.label,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                label = { Text(
                    text = item.label,
                    style = MaterialTheme.typography.tab
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