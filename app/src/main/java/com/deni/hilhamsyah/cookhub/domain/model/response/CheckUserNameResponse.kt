package com.deni.hilhamsyah.cookhub.domain.model.response

import com.google.gson.annotations.SerializedName

data class CheckUserNameResponse(
    @SerializedName("is_username_exists")
    val isUserExists: Boolean
)
