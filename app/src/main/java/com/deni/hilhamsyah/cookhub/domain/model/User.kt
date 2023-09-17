package com.deni.hilhamsyah.cookhub.domain.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("uid")
    val uid: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("user_name")
    val userName: UserName?,

    @SerializedName("first_name")
    val firstName: String?,

    @SerializedName("last_name")
    val lastName: String?,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("profile_image")
    val profileImage: String?
)

data class UserName(
    @SerializedName("value")
    val value: String,

    @SerializedName("last_changed")
    val lastChanged: Long?
)