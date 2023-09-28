package com.deni.hilhamsyah.cookhub.domain.repository

import android.net.Uri
import com.deni.hilhamsyah.cookhub.util.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseStorageRepository {

    fun setProfileImage(uri: Uri, imageName: String): Flow<Resource<String>>

    fun getProfileImage(imageUrl: String): Flow<Resource<ByteArray>>
}