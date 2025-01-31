package com.example.headhunter.models

import com.example.headhunter.models.dto.offer.ButtonDto
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: String? = null,
    val title: String,
    val link: String,
    val button: ButtonDto? = null
)
