package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val movies = getMovies()
    Scaffold(
        topBar = { AppBar("Movie App", Color.White, MaterialTheme.colorScheme.primary) },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        MovieList(movies, Modifier.padding(padding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, titleColor: Color, backgroundColor: Color) {
    CenterAlignedTopAppBar(
        title = { Text(title, color = titleColor) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor
        )
    )
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Star, contentDescription = "Watchlist") },
            label = { Text("Watchlist") },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun MovieList(movies: List<Movie>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            MovieCard(movie)
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.animateContentSize()) {
            MovieImage(movie.images.first())
            MovieInfo(movie.title, expanded, onExpandClicked = { expanded = !expanded })
            ExpandableContent(visible = expanded, movie = movie)
        }
    }
}

@Composable
fun MovieImage(imageUrl: String) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = { crossfade(true) }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Add to favorites",
            modifier = Modifier
                .padding(top = 8.dp, end = 8.dp) // Adjust the padding to position the icon as needed
                .align(Alignment.TopEnd),
            tint = Color.White
        )
    }
}

@Composable
fun MovieInfo(title: String, expanded: Boolean, onExpandClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        IconToggleButton(
            checked = expanded,
            onCheckedChange = { onExpandClicked() }
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand details",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ExpandableContent(visible: Boolean, movie: Movie) {
    AnimatedVisibility(visible = visible) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            MovieDetails("Director", movie.director)
            MovieDetails("Released", movie.year)
            MovieDetails("Genre", movie.genre)
            MovieDetails("Actors", movie.actors)
            MovieDetails("Plot", movie.plot)
        }
    }
}

@Composable
fun MovieDetails(label: String, value: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label: ",
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppMAD24Theme {
        MainScreen()
    }
}
