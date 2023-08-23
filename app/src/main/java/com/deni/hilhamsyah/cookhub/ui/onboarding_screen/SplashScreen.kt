package com.deni.hilhamsyah.cookhub.ui.onboarding_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deni.hilhamsyah.cookhub.navigation.Screen

@Composable
fun SplashScreen(
    onboardingViewModel: OnboardingViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        navController.popBackStack()
        if (onboardingViewModel.isLoggedIn()) navController.navigate(Screen.HomeScreen.route)
        else navController.navigate(Screen.OnboardingScreen.route)
    }

    Text(
        text = "Splash Screen",
        fontSize = 50.sp
    )
}