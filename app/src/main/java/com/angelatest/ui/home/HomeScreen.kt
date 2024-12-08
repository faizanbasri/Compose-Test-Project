package com.angelatest.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.angelatest.MainViewModel
import com.angelatest.ui.home.homeCard.DrugCard
import java.util.Calendar

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(), onCardClick: (String) -> Unit
) {
    val drugList by viewModel.problemsList.collectAsState()
    val username by viewModel.username.collectAsState()
    val error by viewModel.error.collectAsState()
    val greeting = getGreeting()

    LaunchedEffect(Unit) {
        viewModel.fetchDrugs()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Text(
                text = "${username.ifEmpty { "Hello" }}, $greeting",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            LazyColumn {
                items(drugList) { drug ->
                    DrugCard(drug, onCardClick)
                }
            }
            if (drugList.isEmpty() && error.isNotEmpty()) {
                Text(
                    text = error,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                )
            }
        }
    }


}

fun getGreeting(): String {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return when (hour) {
        in 5..12 -> "Good Morning"
        in 12..17 -> "Good Afternoon"
        else -> "Good Evening"
    }
}