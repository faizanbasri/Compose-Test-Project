package com.angelatest.ui.home.homeCard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrugCard(drug: Map<String, String>, onCardClick: (String) -> Unit) {
    Card(
        onClick = { onCardClick(drug["name"] ?: "") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${drug["name"]}", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Dose: ${drug["dose"]}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Strength: ${drug["strength"]}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}