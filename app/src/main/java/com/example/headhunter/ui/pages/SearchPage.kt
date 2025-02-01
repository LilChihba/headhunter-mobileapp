package com.example.headhunter.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox
import androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.headhunter.R
import com.example.headhunter.models.Response
import com.example.headhunter.modules.getDeclension
import com.example.headhunter.ui.elements.CardVacancy
import com.example.headhunter.ui.elements.Recommendation
import com.example.headhunter.ui.theme.Blue
import com.example.headhunter.ui.theme.Grey2
import com.example.headhunter.ui.theme.Grey4
import com.example.headhunter.ui.theme.Shadows
import com.example.headhunter.ui.theme.White
import com.example.headhunter.ui.theme.text1
import com.example.headhunter.ui.theme.title2
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SearchPage(
    data: Response? = null
) {
    val isNextPage = remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

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
                                        if (!isNextPage.value) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.search),
                                                contentDescription = "search",
                                                tint = Grey4
                                            )
                                        } else {
                                            Button(
                                                onClick = { isNextPage.value = false },
                                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                                contentPadding = PaddingValues(0.dp),
                                                modifier = Modifier
                                                    .size(40.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.back),
                                                    contentDescription = "back",
                                                    tint = White
                                                )
                                            }
                                        }
                                    },
                                    placeholder = {
                                        Text(
                                            text =  if (!isNextPage.value) "Должность, ключевые слова" else "Должность по подходящим вакансиям",
                                            style = MaterialTheme.typography.text1,
                                            overflow = TextOverflow.Ellipsis,
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
                            contentPadding = PaddingValues(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Grey2),
                            modifier = Modifier
                                .size(50.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.filter),
                                tint = White,
                                contentDescription = "filter",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }
                if (isNextPage.value) {
                    Box(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Box {
                            Text(
                                text = "${data.offers.size} ${getDeclension(data.offers.size, "вакансия", "вакансий", "вакансий")}",
                                style = MaterialTheme.typography.text1,
                                color = White
                            )
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                        ){
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues(0.dp),
                                shape = RectangleShape,
                                modifier = Modifier
                                    .height(17.dp)
                            ) {
                                Row {
                                    Text(
                                        text = "По соответствию",
                                        style = MaterialTheme.typography.text1,
                                        color = Blue
                                    )
                                    Icon(
                                        painter = painterResource(R.drawable.sort),
                                        contentDescription = "sort",
                                        tint = Blue,
                                        modifier = Modifier
                                            .padding(start = 4.dp, top = 2.dp)
                                            .size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                if (data.offers.isNotEmpty() && !isNextPage.value) {
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
                }
                if (!isNextPage.value) {
                    Text(
                        text = "Вакансии для вас",
                        style = MaterialTheme.typography.title2,
                        color = White,
                        modifier = Modifier
                            .padding(top = 35.dp)
                    )
                }
                if (data.vacancies.isNotEmpty()) {
                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier.padding(top = 18.dp)
                    ) {
                        items(
                            count = data.vacancies.size,
                            key ={
                                data.vacancies[it].id
                            },
                            itemContent = { index ->
                                if(!isNextPage.value) {
                                    if(index <= 2) {
                                        CardVacancy(data.vacancies[index])
                                    } else {
                                        return@items
                                    }
                                } else {
                                    CardVacancy(data.vacancies[index])
                                }
                            }
                        )
                        item {
                            if (!isNextPage.value) {
                                Button(
                                    onClick = {
                                        coroutineScope.launch {
                                            isNextPage.value = true
                                            lazyListState.animateScrollToItem(index = 0)
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    modifier = Modifier
                                        .padding(top = 8.dp, bottom = 8.dp)
                                        .fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = "Ещё ${data.vacancies.size} ${getDeclension(data.vacancies.size, "вакансия", "вакансий", "вакансий")}",
                                        style = MaterialTheme.typography.text1,
                                        color = White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}