package com.deni.hilhamsyah.cookhub.domain.model

import com.google.gson.annotations.SerializedName

data class WriteTimeResponse(
    @SerializedName("_writeTime") val writeTime: WriteTime
)

data class WriteTime(
    @SerializedName("_seconds") val seconds: Long?,
    @SerializedName("_nanoseconds") val nanoseconds: Long?
)
