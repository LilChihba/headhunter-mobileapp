package com.example.headhunter.modules

fun getDeclension(count: Int, one: String, few: String, many: String): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> one
        count % 10 in 2..4 && count % 100 !in 12..14 -> few
        else -> many
    }
}