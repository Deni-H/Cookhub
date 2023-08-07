package com.deni.hilhamsyah.cookhub.domain.model

data class User(
    val me: Boolean,
    val id: String?,
    val email: String?,
    val userName: String,
    val firstName: String,
    val lastName: String?,
    val bio: String?,
    val profileImage: String?,
    val followingCount: Int,
    val followersCount: Int
)
