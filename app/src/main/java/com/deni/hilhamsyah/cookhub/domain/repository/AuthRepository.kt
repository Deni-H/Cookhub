package com.deni.hilhamsyah.cookhub.domain.repository

import com.deni.hilhamsyah.cookhub.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginWithCredentials(credentials: AuthCredential): Flow<Resource<Int>>

    fun loginWithEmailAndPassword(email: String, password: String): Flow<Resource<Int>>

    fun registerWithEmailAndPassword(email: String, password: String): Flow<Resource<Int>>

    fun getCurrentUser(): FirebaseUser?

    fun getIdToken(forceRefresh: Boolean): Flow<Resource<String>>

    suspend fun resetPassword(email: String)

    fun logout()
}