package com.example.headhunter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headhunter.models.Response
import com.example.headhunter.modules.TrimmerString
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResponseViewModel(
    jsonString: String = ""
): ViewModel() {
    private val _response = MutableStateFlow<Response?>(null)
    val response: StateFlow<Response?> = _response

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
}