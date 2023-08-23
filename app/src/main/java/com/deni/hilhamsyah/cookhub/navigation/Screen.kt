package com.deni.hilhamsyah.cookhub.navigation

sealed class Screen(val route: String) {
    object OnboardingScreen: Screen(Routes.ONBOARDING_SCREEN)
}

object Routes {
    const val ONBOARDING_SCREEN = "onboarding_screen"
}
