package com.example.healthcare.MVI.domain.repositories

import com.example.healthcare.MVI.domain.models.TrainingModel
import kotlinx.coroutines.flow.Flow

interface TrainingRepository {
    fun getTrainingPrograms(): Flow<List<TrainingModel>>
}
