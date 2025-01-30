package com.example.headhunter.models.dto.vacancy

import kotlinx.serialization.Serializable

@Serializable
data class SalaryDto(
    val short: String? = null,
    val full: String
)
