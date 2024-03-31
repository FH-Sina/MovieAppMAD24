package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.MovieList
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.widgets.BottomBar
import com.example.movieappmad24.widgets.TopBar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBar(title = "Movie App") },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        MovieListView(innerPadding, navController)
    }
}

@Composable
private fun MovieListView(paddingValues: PaddingValues, navController: NavController) {
    MovieList(
        modifier = Modifier.padding(paddingValues),
        movies = getMovies(),
        navController = navController
    )
}
