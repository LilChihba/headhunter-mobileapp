package com.example.headhunter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headhunter.model.Vacancy
import com.example.headhunter.repository.VacancyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VacancyViewModel: ViewModel() {
    private val repository = VacancyRepository()

    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    val vacancies: StateFlow<List<Vacancy>> = _vacancies

    init {
        loadVacancies()
    }

    private fun loadVacancies() {
        viewModelScope.launch {
            _vacancies.value = repository.getVacancy()
        }
    }
}