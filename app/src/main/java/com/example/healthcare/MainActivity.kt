package com.example.healthcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthcare.MVI.presentation.ui.TrainingNavHost
import com.example.healthcare.MVI.presentation.vm.TrainingViewModel
import com.example.healthcare.MVI.domain.use_cases.GetTrainingProgramsUseCase
import com.example.healthcare.MVI.data.data_sources.TrainingDataSource
import com.example.healthcare.MVI.data.repositories_imp.TrainingRepositoryImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthcareApp()
        }
    }
}

@Composable
fun HealthcareApp() {
    val viewModel: TrainingViewModel = viewModel(factory = TrainingViewModelFactory())
    TrainingNavHost(viewModel)
}

class TrainingViewModelFactory : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        val repository = TrainingRepositoryImpl(TrainingDataSource())
        val useCase = GetTrainingProgramsUseCase(repository)
        return TrainingViewModel(useCase) as T
    }
}
