package com.example.healthcare.MVI.data.repositories_imp


import com.example.healthcare.MVI.data.data_sources.TrainingDataSource
import com.example.healthcare.MVI.domain.models.TrainingModel
import com.example.healthcare.MVI.domain.repositories.TrainingRepository
import kotlinx.coroutines.flow.Flow

class TrainingRepositoryImpl(private val dataSource: TrainingDataSource) : TrainingRepository {
    override fun getTrainingPrograms(): Flow<List<TrainingModel>> {
        return dataSource.getTrainingPrograms()
    }
}
