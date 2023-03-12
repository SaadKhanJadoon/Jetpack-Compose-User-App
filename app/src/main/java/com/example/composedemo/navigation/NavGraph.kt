package com.example.composedemo.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composedemo.ui.HomeScreenPreview
import com.example.composedemo.ui.UserDetailsPreview
import com.example.composedemo.utils.Screen
import com.example.composedemo.viewmodel.UserViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController, viewModel: UserViewModel, context: Context
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreenPreview(navController = navController, viewModel = viewModel)
        }

        composable(route = Screen.Details.route) {
            UserDetailsPreview(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }
    }
}