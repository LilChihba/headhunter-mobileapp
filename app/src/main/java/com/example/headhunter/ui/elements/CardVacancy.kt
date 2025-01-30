package com.example.headhunter.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.headhunter.ui.theme.Grey2

@Preview
@Composable
fun CardVacancy() {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(containerColor = Grey2),
        shape = ShapeDefaults.Small,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card {

        }
    }
}