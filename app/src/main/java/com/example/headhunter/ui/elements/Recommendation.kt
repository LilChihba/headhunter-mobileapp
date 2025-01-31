package com.example.headhunter.ui.elements

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.headhunter.R
import com.example.headhunter.models.Offer
import com.example.headhunter.ui.theme.Blue
import com.example.headhunter.ui.theme.DarkBlue
import com.example.headhunter.ui.theme.DarkGreen
import com.example.headhunter.ui.theme.Green
import com.example.headhunter.ui.theme.Grey1
import com.example.headhunter.ui.theme.White
import com.example.headhunter.ui.theme.text1
import com.example.headhunter.ui.theme.title4

@Composable
fun Recommendation(
    offer: Offer,
    context: Context
) {
    Button(
        onClick = {
            val uri = Uri.parse(offer.link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Grey1),
        shape = ShapeDefaults.Small,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.padding(end = 10.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Grey1
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(135.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .size(32.dp)
                ) {
                    var color: Color = DarkGreen
                    if(offer.id == "near_vacancies") {
                        color = DarkBlue
                    }
                    if(offer.id != null) {
                        Box() {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Transparent, CircleShape)
                                    .background(color)
                            )
                            val iconRes = when(offer.id) {
                                "near_vacancies" -> mapOf(R.drawable.map to Blue)
                                "level_up_resume" -> mapOf(R.drawable.star to Green)
                                "temporary_job" -> mapOf(R.drawable.list to Green)
                                else -> return@Box
                            }
                            for(data in iconRes) {
                                Icon(
                                    painter = painterResource(data.key),
                                    tint = data.value,
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .padding(top = 7.dp, start = 7.dp)
                                        .size(18.dp)
                                )
                            }
                        }
                    }
                }
                Text(
                    text = offer.title,
                    style = MaterialTheme.typography.title4,
                    lineHeight = 20.sp,
                    color = White,
                    maxLines = if(offer.button != null) 2 else 3,
                    modifier = Modifier.padding(start = 8.dp, top = 18.dp, end = 8.dp)
                )
                if(offer.button != null) {
                    Text(
                        text = offer.button.text,
                        color = Green,
                        style = MaterialTheme.typography.text1,
                        modifier = Modifier.padding(start = 8.dp, top = 10.dp, end = 20.dp)
                    )
                }
            }
        }
    }
}