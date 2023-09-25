package com.deni.hilhamsyah.cookhub.ui.home_screen

import com.deni.hilhamsyah.cookhub.domain.model.User

data class UserProfileState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val fail: String? = null,
)
