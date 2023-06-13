package com.mmj.movieapp.presentation.util.resource.route

sealed class AppScreen(val route: String) {
    object HomeScreen : AppScreen(ConstantAppScreenName.HOME_SCREEN)
    object DetailsScreen : AppScreen(ConstantAppScreenName.DETAILS_SCREEN)
}


object ConstantAppScreenName {
    const val HOME_SCREEN = "home_screen"
    const val DETAILS_SCREEN = "details_screen"
}