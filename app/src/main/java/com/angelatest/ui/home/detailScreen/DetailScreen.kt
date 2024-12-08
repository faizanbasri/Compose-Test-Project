package com.angelatest.ui.home.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(drug: Map<String, String>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .wrapContentWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text("Name", style = MaterialTheme.typography.headlineSmall)
                    Text("Dose", style = MaterialTheme.typography.headlineSmall)
                    Text("Strength", style = MaterialTheme.typography.headlineSmall)
                }

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text("${drug["name"]}", style = MaterialTheme.typography.bodyMedium)
                    Text("${drug["dose"]}", style = MaterialTheme.typography.bodyMedium)
                    Text("${drug["strength"]}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}