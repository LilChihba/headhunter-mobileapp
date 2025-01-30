package com.example.headhunter.models

import com.example.headhunter.models.dto.vacancy.AddressDto
import com.example.headhunter.models.dto.vacancy.ExperienceDto
import com.example.headhunter.models.dto.vacancy.SalaryDto
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    val id: String,
    val lookingNumber: Int? = null,
    val title: String,
    val address: AddressDto,
    val company: String,
    val experience: ExperienceDto,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryDto,
    val schedules: List<String>,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String? = null,
    val questions: List<String>
)
