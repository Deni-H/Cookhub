package com.deni.hilhamsyah.cookhub.ui.home_screen

data class GetImageProfileState(
    val isLoading: Boolean = false,
    val image: ByteArray? = null,
    val fail: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetImageProfileState

        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false

        return true
    }

    override fun hashCode(): Int {
        return image?.contentHashCode() ?: 0
    }
}