package com.example.healthcare.MVI.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.healthcare.MVI.presentation.vm.TrainingViewModel

@Composable
fun TrainingNavHost(viewModel: TrainingViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "trainingList") {
        composable("trainingList") {
            TrainingListScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            "enroll/{trainingId}",
            arguments = listOf(navArgument("trainingId") { type = NavType.IntType })
        ) { backStackEntry ->
            val trainingId = backStackEntry.arguments?.getInt("trainingId") ?: 0
            EnrollmentScreen(trainingId = trainingId, navController = navController, viewModel = viewModel) // ✅ Fixed: Pass `viewModel`
        }
    }
}
