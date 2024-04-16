package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.Screen
import com.example.movieappmad24.viewmodels.MoviesViewModel
import com.example.movieappmad24.widgets.MovieList
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun WatchlistScreen(
    navController: NavController,
    moviesViewModel: MoviesViewModel
){
    Scaffold (
        topBar = {
            SimpleTopAppBar(title = "Your Watchlist")
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        }
    ){ innerPadding ->
        // Fetch only favorite movies from the ViewModel
        val favoriteMovies = moviesViewModel.favoriteMovies

        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = favoriteMovies,
            navController = navController,
            viewModel = moviesViewModel,
            onFavoriteClick = { movieId ->
                moviesViewModel.toggleFavoriteMovie(movieId)
            },
            onItemClick = { movieId ->
                navController.navigate(Screen.DetailScreen.withId(movieId))
            }
        )
    }
}