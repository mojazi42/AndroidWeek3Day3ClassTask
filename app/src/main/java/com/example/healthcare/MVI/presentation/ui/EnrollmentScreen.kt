package com.example.healthcare.MVI.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthcare.MVI.presentation.vm.EnrollmentState
import com.example.healthcare.MVI.presentation.vm.TrainingViewModel
import com.example.healthcare.utils.toEnrollmentMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnrollmentScreen(trainingId: Int, navController: NavController, viewModel: TrainingViewModel) {
    val enrollmentState by viewModel.enrollmentState.collectAsState()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Enroll in Training") }) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (enrollmentState) {
                is EnrollmentState.Idle -> EnrollmentUI(trainingId, viewModel)
                is EnrollmentState.Loading -> CircularProgressIndicator()
                is EnrollmentState.Success -> SuccessUI((enrollmentState as EnrollmentState.Success).trainingId, navController)
                is EnrollmentState.Error -> ErrorUI((enrollmentState as EnrollmentState.Error).message)
            }
        }
    }
}

@Composable
fun EnrollmentUI(trainingId: Int, viewModel: TrainingViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Ready to enroll in Training ID: $trainingId", style = MaterialTheme.typography.titleMedium)
        Button(onClick = { viewModel.enrollInTraining(trainingId) }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Enroll")
        }
    }
}

@Composable
fun SuccessUI(trainingId: Int, navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(trainingId.toEnrollmentMessage(), style = MaterialTheme.typography.bodyLarge)
        Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Back to Training List")
        }
    }
}

@Composable
fun ErrorUI(errorMessage: String) {
    Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyLarge)
}
