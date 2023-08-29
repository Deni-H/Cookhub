package com.deni.hilhamsyah.cookhub.ui.auth_screen

data class AuthState(
    val isLoading: Boolean = false,
    val success: String? = null,
    val fail: String? = null
)