package com.deni.hilhamsyah.cookhub.data.remote

import com.deni.hilhamsyah.cookhub.domain.repository.AuthRepository
import com.deni.hilhamsyah.cookhub.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl (
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun loginWithCredentials(credentials: AuthCredential): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading())
            firebaseAuth.signInWithCredential(credentials).await()
            emit(Resource.Success(SUCCESS))
        }.catch {
            emit(Resource.Error(it))
        }
    }

    override fun loginWithEmailAndPassword(email: String, password: String): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading())
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(SUCCESS))
        }.catch {
            emit(Resource.Error(it))
        }
    }

    override fun registerWithEmailAndPassword(email: String, password: String): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.sendEmailVerification()?.await()
            emit(Resource.Success(SUCCESS))
        }.catch {
            emit(Resource.Error(it))
        }
    }

    override fun isLoggedIn(): Boolean {
        return getCurrentUser() != null
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun getIdToken(forceRefresh: Boolean): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading())
            val result = getCurrentUser()?.getIdToken(forceRefresh)?.await()
            val token = result?.token!!
            emit(Resource.Success(token))
        }.catch {
            emit(Resource.Error(it))
        }
    }

    override suspend fun resetPassword(email: String): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading())
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(Resource.Success(SUCCESS))
        }.catch {
            emit(Resource.Error(it))
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    companion object {
        private const val SUCCESS = 200
    }
}