package com.ihm.anacofisk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComponent(navController: NavHostController, recipeVM: RecipeViewModel) {
    val recipeVM = recipeVM

    NavHost(navController = navController, startDestination = Screens.HomeScreen.name) {
        composable(Screens.HomeScreen.name) {
            Home(navController = navController, recipeVM = recipeVM)
        }

        composable(
            route = Screens.RecipeDetailScreen.name
        ) {
            RecipeDetailScreen(
                backAction = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                recipe = recipeVM.getSelectedRecipe().collectAsState(initial = null).value,
                recipeVM = recipeVM
            )
        }

        composable(
            route = Screens.AdvisorScreen.name
        ) {
            AddRecipeScreen(
                backAction = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                recipeVM = recipeVM
            )
        }
    }
}