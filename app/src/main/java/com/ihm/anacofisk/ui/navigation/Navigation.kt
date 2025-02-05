package com.ihm.anacofisk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ihm.anacofisk.Home
import com.ihm.anacofisk.ui.screens.AdvisorScreen
import com.ihm.anacofisk.viewmodel.GameViewModel

@Composable
fun NavigationComponent(navController: NavHostController, gameVM: GameViewModel) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.name) {
        composable(Screens.HomeScreen.name) {
            Home(navController = navController, gameVM = gameVM)
        }

        composable(
            route = Screens.AdvisorScreen.name
        ) {
            AdvisorScreen(
                backAction = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                gameVM = gameVM
            )
        }
    }
}