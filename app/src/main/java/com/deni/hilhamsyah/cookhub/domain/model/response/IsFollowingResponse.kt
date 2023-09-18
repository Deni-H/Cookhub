package com.deni.hilhamsyah.cookhub.domain.model.response

import com.google.gson.annotations.SerializedName

data class IsFollowingResponse(
    @SerializedName("following")
    val isFollowing: Boolean
)
