package com.example.movieappmad24.screens

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
fun WatchlistScreen(navController: NavController) {
    Scaffold(
        topBar = { WatchlistTopBar() },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        WatchlistContent(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun WatchlistTopBar() {
    TopBar("Your Watchlist")
}

@Composable
fun WatchlistContent(modifier: Modifier, navController: NavController) {
    MovieList(
        modifier = modifier,
        movies = getMovies(), //should fetch movies added to the user's watchlist
        navController = navController
    )
}
