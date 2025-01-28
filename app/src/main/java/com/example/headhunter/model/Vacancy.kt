package com.example.headhunter.model

import com.example.headhunter.model.dto.vacancy.AddressDto
import com.example.headhunter.model.dto.vacancy.ExperienceDto
import com.example.headhunter.model.dto.vacancy.SalaryDto
import java.util.UUID

data class Vacancy(
    val id: UUID,
    val lookingNumber: Short,
    val title: String,
    val address: AddressDto,
    val company: String,
    val experience: ExperienceDto,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryDto,
    val schedules: List<String>,
    val appliedNumber: Short? = null,
    val description: String,
    val responsibilities: String,
    val questions: List<String>,
)
