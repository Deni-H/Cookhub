package com.deni.hilhamsyah.cookhub.ui.auth_screen.login_screen

import androidx.lifecycle.ViewModel
import com.deni.hilhamsyah.cookhub.domain.repository.AuthRepository
import com.deni.hilhamsyah.cookhub.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    suspend fun loginWithEmailAndPassword(email: String, password: String) {
        authRepository.loginWithEmailAndPassword(email, password).collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _loginState.send(LoginState(isLoading = true))
                }
                is Resource.Success -> {
                    _loginState.send(LoginState(success = "Login success!"))
                }
                is Resource.Error -> {
                    _loginState.send(LoginState(fail = "Invalid email or password"))
                }
            }
        }
    }
}