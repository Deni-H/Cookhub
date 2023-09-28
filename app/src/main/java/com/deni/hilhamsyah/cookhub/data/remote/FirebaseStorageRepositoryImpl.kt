package com.deni.hilhamsyah.cookhub.data.remote

import android.net.Uri
import com.deni.hilhamsyah.cookhub.domain.repository.FirebaseStorageRepository
import com.deni.hilhamsyah.cookhub.util.ErrorMessage
import com.deni.hilhamsyah.cookhub.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseStorageRepositoryImpl @Inject constructor(
    firebaseStorage: FirebaseStorage,
    firebaseAuth: FirebaseAuth
) : FirebaseStorageRepository {

    private val uid = firebaseAuth.uid
    private val storageRef = firebaseStorage.reference

    override fun setProfileImage(uri: Uri, imageName: String): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading())
            val profileRef = storageRef.child("$USERS_REF/$uid/$PROFILE_IMAGE_REF/$imageName")
            val uploadTask = profileRef.putFile(uri)
            val result = uploadTask.await()
            if (result.task.isSuccessful) emit(Resource.Success(result.task.snapshot.metadata!!.path))
            else emit(Resource.Error(message = ErrorMessage.UNKNOWN_ERROR))
        }.catch {
            emit(Resource.Error(it))
        }
    }

    override fun getProfileImage(imageUrl: String): Flow<Resource<ByteArray>> {
        return flow {
            emit(Resource.Loading())
            val profileRef = storageRef.child(imageUrl)
            val result = profileRef.getBytes(ONE_MEGABYTE).await()
            if (result.isNotEmpty()) emit(Resource.Success(result))
            else emit(Resource.Error(message = ErrorMessage.UNKNOWN_ERROR))
        }.catch {
            emit(Resource.Error(it))
        }
    }

    companion object {
        const val PROFILE_IMAGE_REF = "profile_image"
        const val USERS_REF = "users"
        const val ONE_MEGABYTE: Long = 1024 * 1024
    }
}