package com.example.headhunter.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.headhunter.R
import com.example.headhunter.models.Vacancy
import com.example.headhunter.modules.getDeclension
import com.example.headhunter.ui.theme.Blue
import com.example.headhunter.ui.theme.Green
import com.example.headhunter.ui.theme.Grey1
import com.example.headhunter.ui.theme.Grey3
import com.example.headhunter.ui.theme.Grey4
import com.example.headhunter.ui.theme.White
import com.example.headhunter.ui.theme.button2
import com.example.headhunter.ui.theme.text1
import com.example.headhunter.ui.theme.title2
import com.example.headhunter.ui.theme.title3

@Composable
fun CardVacancy(
    vacancy: Vacancy
) {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(containerColor = Grey1),
        shape = ShapeDefaults.Small,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Grey1
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 16.dp)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Box{
                        if(vacancy.lookingNumber != null) {
                            Text(
                                text = "Сейчас просматривает ${vacancy.lookingNumber} ${getDeclension(vacancy.lookingNumber, "человек", "человека", "человек")}",
                                style = MaterialTheme.typography.text1,
                                color = Green
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                    ){
                        if(vacancy.isFavorite) {
                            Icon(
                                painter = painterResource(R.drawable.is_favorite),
                                contentDescription = "isFavorite",
                                tint = Blue,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.favorite),
                                contentDescription = "isFavorite",
                                tint = Grey4,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }
                Text(
                    text = vacancy.title,
                    style = MaterialTheme.typography.title3,
                    color = White,
                    modifier = Modifier
                        .padding(top = 6.dp)
                )
                if(vacancy.salary.short != null) {
                    Text(
                        text = vacancy.salary.short,
                        style = MaterialTheme.typography.title2,
                        color = White,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 4.dp)
                    )
                }
                Text(
                    text = vacancy.address.town,
                    style = MaterialTheme.typography.text1,
                    color = White,
                    modifier = Modifier
                        .padding(top = 6.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp)
                ){
                    Text(
                        text = vacancy.company,
                        style = MaterialTheme.typography.text1,
                        color = White
                    )
                    Icon(
                        painter = painterResource(R.drawable.check),
                        contentDescription = "checked",
                        tint = Grey3,
                        modifier = Modifier
                            .padding(top = 3.dp, start = 6.dp)
                            .size(12.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ){
                    Icon(
                        painter = painterResource(R.drawable.experience),
                        contentDescription = "experience",
                        tint = White,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .size(width = 12.dp, height = 10.dp)
                    )
                    Text(
                        text = vacancy.experience.previewText,
                        style = MaterialTheme.typography.text1,
                        color = White,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
                val dateDay = vacancy.publishedDate.split("-").toTypedArray()[2]
                val dateMonth = vacancy.publishedDate.split("-").toTypedArray()[1]
                val dateMap = when(dateMonth) {
                    "01" -> mapOf("01" to "января")
                    "02" -> mapOf("02" to "февраля")
                    "03" -> mapOf("03" to "марта")
                    "04" -> mapOf("04" to "апреля")
                    "05" -> mapOf("05" to "мая")
                    "06" -> mapOf("06" to "июня")
                    "07" -> mapOf("07" to "июля")
                    "08" -> mapOf("08" to "августа")
                    "09" -> mapOf("09" to "сентября")
                    "10" -> mapOf("10" to "октября")
                    "11" -> mapOf("11" to "ноября")
                    "12" -> mapOf("12" to "декабря")
                    else -> mapOf()
                }
                for(date in dateMap) {
                    Text(
                        text = "Опубликовано ${dateDay.toInt()} ${date.value}",
                        style = MaterialTheme.typography.text1,
                        color = Grey3,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                }
                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                    shape = RoundedCornerShape(80.dp),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                ) {
                    Text(
                        text = "Откликнуться",
                        style = MaterialTheme.typography.button2,
                        color = White
                    )
                }
            }
        }
    }
}