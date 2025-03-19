package com.example.healthcare.MVI.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcare.MVI.domain.use_cases.GetTrainingProgramsUseCase
import com.example.healthcare.MVI.domain.models.TrainingModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TrainingViewModel(private val getTrainingProgramsUseCase: GetTrainingProgramsUseCase) : ViewModel() {

    private val _trainingState = MutableStateFlow<TrainingUIState>(TrainingUIState.Loading)
    val trainingState: StateFlow<TrainingUIState> = _trainingState.asStateFlow()

    private val _enrollmentState = MutableStateFlow<EnrollmentState>(EnrollmentState.Idle)
    val enrollmentState: StateFlow<EnrollmentState> = _enrollmentState.asStateFlow()

    init {
        fetchTrainings()
    }

    private fun fetchTrainings() {
        viewModelScope.launch {
            getTrainingProgramsUseCase.execute()
                .catch { _trainingState.value = TrainingUIState.Error(it.message ?: "Unknown Error") }
                .collect { trainings ->
                    _trainingState.value = if (trainings.isNotEmpty()) {
                        TrainingUIState.Success(trainings)
                    } else {
                        TrainingUIState.Empty
                    }
                }
        }
    }

    fun enrollInTraining(trainingId: Int) {
        viewModelScope.launch {
            _enrollmentState.value = EnrollmentState.Loading
            kotlinx.coroutines.delay(1500) // Simulate API request
            _enrollmentState.value = EnrollmentState.Success(trainingId)
        }
    }
}

sealed class TrainingUIState {
    object Loading : TrainingUIState()
    object Empty : TrainingUIState()
    data class Success(val data: List<TrainingModel>) : TrainingUIState()
    data class Error(val message: String) : TrainingUIState()
}

sealed class EnrollmentState {
    object Idle : EnrollmentState()
    object Loading : EnrollmentState()
    data class Success(val trainingId: Int) : EnrollmentState()
    data class Error(val message: String) : EnrollmentState()
}
