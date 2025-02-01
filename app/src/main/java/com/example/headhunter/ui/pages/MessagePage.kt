package com.example.headhunter.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import com.example.headhunter.ui.theme.Shadows
import com.example.headhunter.ui.theme.White
import com.example.headhunter.ui.theme.title1

@Composable
fun MessagePage() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Shadows)
            .fillMaxSize()
    ) {
        Text(
            text = "Message Screen",
            style = MaterialTheme.typography.title1,
            color = White
        )
    }
}