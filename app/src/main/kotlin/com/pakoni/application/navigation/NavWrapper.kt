package com.pakoni.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pakoni.home.HomeScreen
import com.pakoni.home.HomeScreen
import com.pakoni.home.HomeScreen
import com.pakoni.home.HomeScreen

@Composable
fun NavWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreen(
                navigateToBookMarkS = { navController.navigate(BookMarksS) },
                navigateToConfigS = { navController.navigate(ConfigS) },
            )
        }
//        composable<BookMarksS> {
//            BookMarksS(
//                navigateToHomeS = { navController.navigate(HomeS) },
//                navigateToConfigS = { navController.navigate(ConfigS) },
//            )
//
//        }
//        composable<ConfigS> {
//            ConfigS(
//                navigateToConfigS = { navController.navigate(BookMarksS) },
//                navigateToLoginHomeS = { navController.navigate(HomeS) }
//            )
//        }
    }
}