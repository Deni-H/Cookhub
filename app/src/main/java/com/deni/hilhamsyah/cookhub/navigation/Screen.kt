package com.deni.hilhamsyah.cookhub.navigation

sealed class Screen(val route: String) {
    object OnboardingScreen: Screen(Routes.ONBOARDING_SCREEN)
    object LoginScreen: Screen(Routes.LOGIN_SCREEN)
    object HomeScreen: Screen(Routes.HOME_SCREEN)
    object SplashScreen: Screen(Routes.SPLASH_SCREEN)
    object RegisterScreen: Screen(Routes.REGISTER_SCREEN)
}

object Routes {
    const val ONBOARDING_SCREEN = "onboarding_screen"
    const val LOGIN_SCREEN = "login_screen"
    const val REGISTER_SCREEN = "register_screen"
    const val HOME_SCREEN = "home_screen"
    const val SPLASH_SCREEN = "splash_screen"
}
