package com.pakoni.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pakoni.bookmarks.BookMarksScreen
import com.pakoni.config.ConfigScreen
import com.pakoni.home.HomeScreen


@Composable
fun NavWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreen(
                navigateToBookMarkS = { navController.navigate(BookMarksScreen) },
                navigateToConfigS = { navController.navigate(ConfigScreen) },
            )
        }
        composable<BookMarksScreen> {
            BookMarksScreen(
                navigateToHomeS = { navController.navigate(HomeScreen) },
                navigateToConfigS = { navController.navigate(ConfigScreen) },
            )
        }
        composable<ConfigScreen> {
            ConfigScreen(
                navigateToConfigS = { navController.navigate(BookMarksScreen) },
                navigateToLoginHomeS = { navController.navigate(HomeScreen) }
            )
        }
    }
}