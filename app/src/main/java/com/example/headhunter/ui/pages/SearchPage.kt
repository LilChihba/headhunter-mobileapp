package com.example.headhunter.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox
import androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.headhunter.R
import com.example.headhunter.models.Response
import com.example.headhunter.ui.elements.CardVacancy
import com.example.headhunter.ui.elements.Recommendation
import com.example.headhunter.ui.theme.Grey2
import com.example.headhunter.ui.theme.Grey4
import com.example.headhunter.ui.theme.Shadows
import com.example.headhunter.ui.theme.White
import com.example.headhunter.ui.theme.text1
import com.example.headhunter.ui.theme.title2

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SearchPage(
    data: Response? = null
) {
    if (data == null) {
        return
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Shadows)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { keyboardController?.hide() }
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(start = 16.dp, top = 10.dp, end = 16.dp)
                .fillMaxSize(),
            containerColor = Shadows
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val textSearch = remember { mutableStateOf("") }
                    Box(
                        modifier = Modifier.width(300.dp)
                    ){
                        BasicTextField(
                            value = textSearch.value,
                            onValueChange = { newText -> textSearch.value = newText },
                            textStyle = MaterialTheme.typography.text1.copy(
                                color = Grey4
                            ),
                            singleLine = true,
                            decorationBox = { innerTextField ->
                                OutlinedTextFieldDecorationBox(
                                    value = textSearch.value,
                                    innerTextField = innerTextField,
                                    enabled = true,
                                    singleLine = false,
                                    shape = RoundedCornerShape(8.dp),
                                    colors = outlinedTextFieldColors(
                                        backgroundColor = Grey2,
                                        placeholderColor = Grey4,
                                        focusedBorderColor = Color.Transparent,
                                        unfocusedBorderColor = Color.Transparent,
                                    ),
                                    visualTransformation = VisualTransformation.None,
                                    interactionSource = remember { MutableInteractionSource() },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.search),
                                            contentDescription = "search",
                                            tint = Grey4
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Должность, ключевые слова",
                                            style = MaterialTheme.typography.text1,
                                            color = Grey4
                                        )
                                    }
                                )
                            },
                            modifier = Modifier
                                .width(300.dp)
                                .height(50.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(
                            onClick = {  },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Grey2),
                            modifier = Modifier
                                .size(50.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.filter),
                                tint = White,
                                contentDescription = "filter",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
                LazyRow(
                    modifier = Modifier.padding(top = 18.dp)
                ) {
                    items(
                        count = data.offers.size,
                        key ={
                            data.offers[it].title
                        },
                        itemContent = { index ->
                            Recommendation(data.offers[index], LocalContext.current)
                        }
                    )
                }
                Text(
                    text = "Вакансии для вас",
                    style = MaterialTheme.typography.title2,
                    modifier = Modifier
                        .padding(top = 35.dp)
                )
                CardVacancy()
            }
        }
    }
}