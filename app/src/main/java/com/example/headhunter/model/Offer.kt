package com.example.headhunter.model

import com.example.headhunter.model.dto.offer.ButtonDto

data class Offer(
    val id: String? = null,
    val title: String,
    val button: ButtonDto? = null,
    val link: String,
)
