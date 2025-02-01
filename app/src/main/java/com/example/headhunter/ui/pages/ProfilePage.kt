package com.example.headhunter.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.headhunter.ui.theme.Shadows
import com.example.headhunter.ui.theme.White
import com.example.headhunter.ui.theme.title1

@Composable
fun ProfilePage() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Shadows)
            .fillMaxSize()
    ) {
        Text(
            text = "Profile Screen",
            style = MaterialTheme.typography.title1,
            color = White
        )
    }
}