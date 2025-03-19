package com.example.healthcare.MVI.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.healthcare.MVI.presentation.vm.TrainingViewModel
import com.example.healthcare.MVI.presentation.vm.TrainingUIState
import com.example.healthcare.MVI.domain.models.TrainingModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingListScreen(viewModel: TrainingViewModel, navController: NavController) {
    val state by viewModel.trainingState.collectAsState()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Healthcare Training Programs", style = MaterialTheme.typography.titleLarge) }) }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
            when (state) {
                is TrainingUIState.Loading -> CircularProgressIndicator()
                is TrainingUIState.Success -> TrainingList((state as TrainingUIState.Success).data, navController)
                is TrainingUIState.Empty -> Text(
                    "No training programs available.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                is TrainingUIState.Error -> Text(
                    "Error: ${(state as TrainingUIState.Error).message}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TrainingList(trainings: List<TrainingModel>, navController: NavController) {
    LazyColumn {
        items(trainings) { training ->
            TrainingItem(training, onClick = { navController.navigate("enroll/${training.id}") })
        }
    }
}

@Composable
fun TrainingItem(training: TrainingModel, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(training.name, style = MaterialTheme.typography.titleMedium)
            Text(training.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
