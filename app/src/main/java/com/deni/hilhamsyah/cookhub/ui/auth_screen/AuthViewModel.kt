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

    private val _loginState = Channel<AuthState>()
    val loginState = _loginState.receiveAsFlow()

    private val _resetPassState = Channel<AuthState>()
    val resetPassState = _resetPassState.receiveAsFlow()

    suspend fun loginWithEmailAndPassword(email: String, password: String) {
        authRepository.loginWithEmailAndPassword(email, password).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _loginState.send(AuthState(isLoading = true))
                }
                is Resource.Success -> {
                    _loginState.send(AuthState(success = "Login success!"))
                }
                is Resource.Error -> {
                    _loginState.send(AuthState(fail = "Invalid email or password"))
                }
            }
        }
    }

    suspend fun signInWithCredentials(credential: AuthCredential) {
        authRepository.loginWithCredentials(credentials = credential).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _loginState.send(AuthState(isLoading = true))
                }
                is Resource.Success -> {
                    _loginState.send(AuthState(success = "Login success!"))
                }
                is Resource.Error -> {
                    _loginState.send(AuthState(fail = "Login failed"))
                }
            }
        }
    }

    suspend fun resetPassword(email: String) {
        authRepository.resetPassword(email).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _resetPassState.send(AuthState(isLoading = true))
                }
                is Resource.Success -> {
                    _resetPassState.send(AuthState(success = "Reset password email sent!"))
                }
                is Resource.Error -> {
                    _resetPassState.send(AuthState(fail = "Reset password failed"))
                }
            }
        }
    }
}