package com.deni.hilhamsyah.cookhub.ui.auth_screen.login_screen

data class LoginState(
    val isLoading: Boolean = false,
    val success: String? = null,
    val fail: String? = null
)