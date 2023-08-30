package com.deni.hilhamsyah.cookhub.navigation

sealed class Screen(val route: String) {
    object OnboardingScreen: Screen(Routes.ONBOARDING_SCREEN)
    object LoginScreen: Screen(Routes.LOGIN_SCREEN)
    object HomeScreen: Screen(Routes.HOME_SCREEN)
    object RegisterScreen: Screen(Routes.REGISTER_SCREEN)
    object ForgetPasswordScreen: Screen(Routes.FORGET_PASSWORD_SCREEN)
    object ForgetPasswordSuccessScreen: Screen(Routes.FORGET_PASSWORD_SUCCESS_SCREEN)
}

object Routes {
    const val ONBOARDING_SCREEN = "onboarding_screen"
    const val LOGIN_SCREEN = "login_screen"
    const val REGISTER_SCREEN = "register_screen"
    const val HOME_SCREEN = "home_screen"
    const val FORGET_PASSWORD_SCREEN = "forget_password_screen"
    const val FORGET_PASSWORD_SUCCESS_SCREEN = "forget_password_success_screen"
}
