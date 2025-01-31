package com.example.headhunter.models

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)