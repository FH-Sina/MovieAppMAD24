package com.example.movieappmad24.screens


sealed class GoToScreen(val route: String) {
    object Home: GoToScreen(route = "homeScreen")
    object Details: GoToScreen(route = "detailsScreen")
    object Watchlist: GoToScreen(route = "watchlist")

}