package com.deni.hilhamsyah.cookhub.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deni.hilhamsyah.cookhub.ui.auth_screen.AuthViewModel
import com.deni.hilhamsyah.cookhub.ui.auth_screen.forget_password.ForgetPasswordScreen
import com.deni.hilhamsyah.cookhub.ui.auth_screen.forget_password.ForgetPasswordSuccessScreen
import com.deni.hilhamsyah.cookhub.ui.home_screen.HomeScreen
import com.deni.hilhamsyah.cookhub.ui.auth_screen.login_screen.LoginScreen
import com.deni.hilhamsyah.cookhub.ui.auth_screen.register_screen.RegisterScreen
import com.deni.hilhamsyah.cookhub.ui.create_profile_screen.CreateProfileScreen
import com.deni.hilhamsyah.cookhub.ui.onboarding_screen.OnboardingScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination =
            if (authViewModel.isUserLoggedIn()) Screen.HomeScreen.route
            else Screen.OnboardingScreen.route
    ) {
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
        composable(route = Screen.ForgetPasswordSuccessScreen.route) {
            ForgetPasswordSuccessScreen(navController = navHostController)
        }
        composable(route = Screen.CreateProfileScreen.route) {
            CreateProfileScreen(navController = navHostController)
        }

    }
}