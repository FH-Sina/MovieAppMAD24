package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.viewmodels.MoviesViewModel
import com.example.movieappmad24.widgets.HorizontalScrollableImageView
import com.example.movieappmad24.widgets.MovieRow
import com.example.movieappmad24.widgets.MovieTrailerPlayer
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun DetailScreen(
    movieId: String?,
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    movieId?.let { id ->
        moviesViewModel.movies.find { it.id == id }?.also { movie ->
            DetailLayout(movie, navController, moviesViewModel)
        }
    }
}

@Composable
fun DetailLayout(
    movie: Movie,
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    Scaffold(
        topBar = { DetailTopBar(movie.title, navController) },
        content = { padding ->
            DetailContent(movie, padding, moviesViewModel)
        }
    )
}

@Composable
fun DetailTopBar(title: String, navController: NavController) {
    SimpleTopAppBar(title = title) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Go back"
            )
        }
    }
}

@Composable
fun DetailContent(
    movie: Movie,
    paddingValues: PaddingValues,
    moviesViewModel: MoviesViewModel
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        MovieInfoRow(movie, moviesViewModel)
        MovieImages(movie)
        MovieTrailerSection(movie)
    }
}

@Composable
fun MovieInfoRow(
    movie: Movie,
    moviesViewModel: MoviesViewModel
) {
    MovieRow(
        modifier = Modifier.fillMaxWidth(),
        movie = movie,
        onFavoriteClick = { moviesViewModel.toggleFavoriteMovie(movie.id) },
        onItemClick = { /* This might handle a different navigation or action */ }
    )
}

@Composable
fun MovieImages(movie: Movie) {
    HorizontalScrollableImageView(movie = movie)
}

@Composable
fun MovieTrailerSection(movie: Movie) {
    MovieTrailerPlayer(movieTrailer = movie.trailer)
}
