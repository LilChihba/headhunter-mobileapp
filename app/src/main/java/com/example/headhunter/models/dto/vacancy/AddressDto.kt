package com.example.headhunter.models.dto.vacancy

import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    val town: String,
    val street: String,
    val house: String,
)
