package com.deni.hilhamsyah.cookhub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deni.hilhamsyah.cookhub.ui.forget_password.ForgetPasswordScreen
import com.deni.hilhamsyah.cookhub.ui.auth_screen.home_screen.HomeScreen
import com.deni.hilhamsyah.cookhub.ui.auth_screen.login_screen.LoginScreen
import com.deni.hilhamsyah.cookhub.ui.onboarding_screen.OnboardingScreen
import com.deni.hilhamsyah.cookhub.ui.onboarding_screen.SplashScreen
import com.deni.hilhamsyah.cookhub.ui.register_screen.RegisterScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navHostController)
        }
        composable(route = Screen.OnboardingScreen.route) {
            OnboardingScreen(navController = navHostController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navHostController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navHostController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navHostController)
        }
         composable(route = Screen.ForgetPasswordScreen.route) {
            ForgetPasswordScreen(navController = navHostController)
        }

    }
}