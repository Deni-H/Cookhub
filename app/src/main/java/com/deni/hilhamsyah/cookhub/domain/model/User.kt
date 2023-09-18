package com.deni.hilhamsyah.cookhub.domain.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("user_name")
    val userName: UserName? = null,

    @SerializedName("first_name")
    val firstName: String? = null,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("bio")
    val bio: String? = null,

    @SerializedName("profile_image")
    val profileImage: String? = null
)

data class UserName(
    @SerializedName("value")
    val value: String,

    @SerializedName("last_changed")
    val lastChanged: Long? = null
)