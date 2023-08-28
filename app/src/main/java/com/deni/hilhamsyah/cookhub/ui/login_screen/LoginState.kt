package com.deni.hilhamsyah.cookhub.ui.login_screen

data class LoginState(
    val isLoading: Boolean = false,
    val success: String? = null,
    val fail: String? = null
)