package com.example.healthcare.MVI.domain.use_cases



import com.example.healthcare.MVI.domain.models.TrainingModel
import com.example.healthcare.MVI.domain.repositories.TrainingRepository
import kotlinx.coroutines.flow.Flow

class GetTrainingProgramsUseCase(private val repository: TrainingRepository) {
    fun execute(): Flow<List<TrainingModel>> = repository.getTrainingPrograms()
}
