package com.example.headhunter.models.dto.vacancy

import kotlinx.serialization.Serializable

@Serializable
data class ExperienceDto(
    val previewText: String,
    val text: String,
)
