package com.deni.hilhamsyah.cookhub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deni.hilhamsyah.cookhub.ui.onboarding_screen.OnboardingScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.OnboardingScreen.route
    ) {
        composable(route = Screen.OnboardingScreen.route) {
            OnboardingScreen(navController = navHostController)
        }
    }
}