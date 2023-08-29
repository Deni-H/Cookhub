package com.deni.hilhamsyah.cookhub.ui.auth_screen

import androidx.lifecycle.ViewModel
import com.deni.hilhamsyah.cookhub.domain.repository.AuthRepository
import com.deni.hilhamsyah.cookhub.util.Resource
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = Channel<AuthState>()
    val authState = _authState.receiveAsFlow()

    suspend fun loginWithEmailAndPassword(email: String, password: String) {
        authRepository.loginWithEmailAndPassword(email, password).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _authState.send(AuthState(isLoading = true))
                }
                is Resource.Success -> {
                    _authState.send(AuthState(success = "Login success!"))
                }
                is Resource.Error -> {
                    _authState.send(AuthState(fail = "Invalid email or password"))
                }
            }
        }
    }

    suspend fun registerWithEmailAndPassword(email: String, password: String) {
        authRepository.registerWithEmailAndPassword(email, password).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _authState.send(AuthState(isLoading = true))
                }
                is Resource.Success -> {
                    _authState.send(AuthState(success = "Register success!"))
                }
                is Resource.Error -> {
                    _authState.send(AuthState(fail = "An error occurred"))
                }
            }
        }
    }

    suspend fun signInWithCredentials(credential: AuthCredential) {
        authRepository.loginWithCredentials(credentials = credential).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _authState.send(AuthState(isLoading = true))
                }
                is Resource.Success -> {
                    _authState.send(AuthState(success = "Login success!"))
                }
                is Resource.Error -> {
                    _authState.send(AuthState(fail = "Login failed"))
                }
            }
        }
    }

    suspend fun resetPassword(email: String) {
        authRepository.resetPassword(email).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _authState.send(AuthState(isLoading = true))
                }
                is Resource.Success -> {
                    _authState.send(AuthState(success = "Reset password email sent!"))
                }
                is Resource.Error -> {
                    _authState.send(AuthState(fail = "Reset password failed"))
                }
            }
        }
    }
}