package com.example.movieappmad24

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import coil.compose.AsyncImage
import com.example.movieappmad24.screens.GoToScreen

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movies: List<Movie> = getMovies(),
    navController: NavController
){
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            MovieRow(movie = movie){ movieId ->
                navController.navigate(GoToScreen.Details.route + "/$movieId")
            }
        }
    }
}

@Composable
fun MovieRow(
    movie: Movie,
    onItemClick: (String) -> Unit = {}
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick(movie.id) },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            MovieBanner(imageUrl = movie.images.first())
            MovieInformation(modifier = Modifier.padding(12.dp), movie = movie)
        }
    }
}

@Composable
fun MovieBanner(imageUrl: String) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        MovieImage(imageUrl)
        LikeButton()
    }
}

@Composable
fun MovieImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Movie Image",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LikeButton() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "Add to favorites",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun MovieInformation(modifier: Modifier, movie: Movie) {
    var showDetails by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(movie.title, style = MaterialTheme.typography.titleLarge)
        DetailToggle(showDetails) { showDetails = !showDetails }
    }
    ExpandableInfo(showDetails, modifier, movie)
}

@Composable
fun DetailToggle(isExpanded: Boolean, onToggle: () -> Unit) {
    IconButton(onClick = onToggle) {
        Icon(
            imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = "Show more details"
        )
    }
}

@Composable
fun ExpandableInfo(isExpanded: Boolean, modifier: Modifier, movie: Movie) {
    AnimatedVisibility(
        visible = isExpanded,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Column(modifier = modifier) {
            Text("Director: ${movie.director}", style = MaterialTheme.typography.bodyMedium)
            Text("Release: ${movie.year}", style = MaterialTheme.typography.bodyMedium)
            Text("Genre: ${movie.genre}", style = MaterialTheme.typography.bodyMedium)
            Text("Cast: ${movie.actors}", style = MaterialTheme.typography.bodyMedium)
            Divider()
            MoviePlot(plot = movie.plot)
        }
    }
}

@Composable
fun MoviePlot(plot: String) {
    Text(plot, style = MaterialTheme.typography.bodyMedium)
}
