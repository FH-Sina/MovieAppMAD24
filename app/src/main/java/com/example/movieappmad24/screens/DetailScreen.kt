package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad24.MovieImage
import com.example.movieappmad24.MovieRow
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movieId: String?, navController: NavController) {
    val movie = getMovies().find { it.id == movieId }

    Scaffold(
        topBar = {
            DetailScreenTopBar(movie?.title ?: "Movie Details", navController)
        }
    ) { paddingValues ->
        DetailContent(movie, paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopBar(title: String, navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors()
    )
}

@Composable
fun DetailContent(movie: Movie?, paddingValues: PaddingValues) {
    movie?.let {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            MovieRow(movie = it)
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow {
                items(it.images) { image ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(200.dp)
                    ) {
                        MovieImage(imageUrl = image)
                    }
                }
            }
        }
    }
}
