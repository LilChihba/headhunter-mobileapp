package com.example.headhunter.ui.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.headhunter.models.Vacancy
import com.example.headhunter.ui.pages.FavoritePage
import com.example.headhunter.ui.pages.MessagePage
import com.example.headhunter.ui.pages.ProfilePage
import com.example.headhunter.ui.pages.ResponsePage
import com.example.headhunter.ui.pages.SearchPage
import com.example.headhunter.ui.pages.VacancyPage
import com.example.headhunter.viewmodels.ResponseViewModel
import com.example.headhunter.modules.fromJson
import java.net.URLDecoder

@Composable
fun MainScreen(
    viewModel: ResponseViewModel = viewModel()
) {
    val jsonString = LocalContext.current.assets.open("json.json").bufferedReader().use { it.readText() }
    viewModel.loadResponse(jsonString)
    val responseData by viewModel.response.observeAsState()

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
            composable(BottomNavItem.SearchPage.route) { SearchPage(responseData, viewModel, navController) }
            composable(BottomNavItem.FavoritePage.route) { FavoritePage(responseData?.vacancies, viewModel, navController) }
            composable(BottomNavItem.ResponsePage.route) { ResponsePage() }
            composable(BottomNavItem.MessagePage.route) { MessagePage() }
            composable(BottomNavItem.ProfilePage.route) { ProfilePage() }
            composable(
                route = "vacancy/{vacancyString}",
                arguments = listOf(
                    navArgument("vacancyString") {
                        type = NavType.StringType
                    }
                )
            ) {
                it.arguments?.getString("vacancyString")?.let { vacancyString ->
                    val vacancy = URLDecoder.decode(vacancyString, "UTF-8").fromJson(Vacancy::class.java)
                    VacancyPage(vacancy)
                }
            }
        }
    }
}