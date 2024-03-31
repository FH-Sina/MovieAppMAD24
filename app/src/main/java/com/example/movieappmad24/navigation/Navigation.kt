package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = GoToScreen.Home.route) {
        composable(GoToScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = GoToScreen.Details.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { entry ->
            DetailScreen(movieId = entry.arguments?.getString("movieId"), navController = navController)
        }
        composable(GoToScreen.Watchlist.route) {
            WatchlistScreen(navController)
        }
    }
}
