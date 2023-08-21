package com.deni.hilhamsyah.cookhub.domain.model

data class User(
    val id: String,
    val email: String,
    val userName: String?,
    val firstName: String,
    val lastName: String?,
    val bio: String?,
    val profileImage: String?
)