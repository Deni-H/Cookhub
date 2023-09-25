package com.deni.hilhamsyah.cookhub.ui.state

data class NewUserState(
    val isLoading: Boolean = false,
    val isNewUser: Boolean?= null,
    val fail: String? = null
)
