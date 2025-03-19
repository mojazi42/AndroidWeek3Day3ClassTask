package com.example.healthcare.MVI.data.data_sources

import com.example.healthcare.MVI.domain.models.TrainingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TrainingDataSource {
    fun getTrainingPrograms(): Flow<List<TrainingModel>> = flow {
        val trainingPrograms = listOf(
            TrainingModel(1, "First Aid Basics", "Learn essential life-saving skills"),
            TrainingModel(2, "Advanced Cardiology", "Detailed study on heart diseases"),
            TrainingModel(3, "Emergency Response", "How to handle medical emergencies")
        )
        emit(trainingPrograms)
    }
}
