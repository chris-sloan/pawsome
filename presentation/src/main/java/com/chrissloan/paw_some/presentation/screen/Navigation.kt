package com.chrissloan.paw_some.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chrissloan.paw_some.presentation.screen.allbreeds.AllBreedsScreen
import com.chrissloan.paw_some.presentation.screen.breeddetail.BreedDetailScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Breeds.route
    ) {
        composable(route = Screen.Breeds.route) {
            AllBreedsScreen(
                onBreedSelected = { breed ->
                    navController.navigate(
                        route = Screen.BreedDetail.route.replace(
                            oldValue = "{breedId}",
                            newValue = breed.id,
                            ignoreCase = false
                        )
                    )
                }
            )
        }
        composable(
            route = Screen.BreedDetail.route,
            arguments = listOf(navArgument("breedId") { type = NavType.StringType })
        ) { backStackEntry ->
            BreedDetailScreen(
                breedId = backStackEntry.arguments?.getString("breedId").orEmpty(),
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Breeds : Screen("all_breeds")
    object BreedDetail : Screen("breed_detail/{breedId}")
}

