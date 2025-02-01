package com.example.headhunter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headhunter.models.Response
import com.example.headhunter.models.Vacancy
import com.example.headhunter.modules.TrimmerString
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class ResponseViewModel(
    jsonString: String = ""
): ViewModel() {
    private val _response = MutableLiveData<Response?>(null)
    val response: LiveData<Response?> get() = _response

    init {
        loadResponse(jsonString)
    }

    fun loadResponse(jsonString: String) {
        viewModelScope.launch {
            val gson = GsonBuilder()
                .registerTypeAdapter(String::class.java, TrimmerString())
                .create()

            _response.value = gson.fromJson(jsonString, Response::class.java)
        }
    }

    fun updateVacancyFavorite(vacancy: Vacancy) {
        val currentResponse = _response.value
        currentResponse?.vacancies?.find { it.id == vacancy.id }?.isFavorite = vacancy.isFavorite
        _response.value = currentResponse
    }
}