package com.example.headhunter.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.headhunter.models.Vacancy
import com.example.headhunter.modules.getDeclension
import com.example.headhunter.ui.elements.CardVacancy
import com.example.headhunter.ui.theme.Grey3
import com.example.headhunter.ui.theme.Shadows
import com.example.headhunter.ui.theme.White
import com.example.headhunter.ui.theme.text1
import com.example.headhunter.ui.theme.title1
import com.example.headhunter.viewmodels.ResponseViewModel

@Composable
fun FavoritePage(
    vacancies: List<Vacancy>? = null,
    viewModel: ResponseViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(Shadows)
            .padding(start = 16.dp, top = 32.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Избранное",
            style = MaterialTheme.typography.title1,
            color = White
        )
        if (vacancies == null) {
            return
        }
        val countVacancy = vacancies.count { it.isFavorite }
        Text(
            text = "$countVacancy ${getDeclension(countVacancy, "вакансия", "вакансии", "вакансий")}",
            style = MaterialTheme.typography.text1,
            color = Grey3,
            modifier = Modifier
                .padding(top = 24.dp)
        )
        if (vacancies.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(top = 18.dp)
            ) {
                items(
                    count = vacancies.size,
                    key = {
                        vacancies[it].id
                    },
                    itemContent = { index ->
                        if(vacancies[index].isFavorite) {
                            CardVacancy(vacancies[index], viewModel, navController)
                        }
                    }
                )
            }
        }
    }
}