package com.deni.hilhamsyah.cookhub.ui.auth_screen

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import com.deni.hilhamsyah.cookhub.domain.repository.AuthRepository
import com.deni.hilhamsyah.cookhub.domain.repository.UserRepository
import com.deni.hilhamsyah.cookhub.ui.state.NewUserState
import com.deni.hilhamsyah.cookhub.util.Constant
import com.deni.hilhamsyah.cookhub.util.ErrorMessage
import com.deni.hilhamsyah.cookhub.util.Resource
import com.deni.hilhamsyah.cookhub.util.TAG
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _authState = Channel<AuthState>()
    val authState = _authState.receiveAsFlow()

    private val _isNewUserState = Channel<NewUserState>()
    val isNewUserState = _isNewUserState.receiveAsFlow()

    suspend fun isNewUser() {
        userRepository.getUserProfile().collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _isNewUserState.send(NewUserState(isLoading = true))
                }
                is Resource.Success -> {
                    _isNewUserState.send(NewUserState(isNewUser = false))
                }
                is Resource.Error -> {
                    if (result.message == ErrorMessage.NOT_FOUND) {
                        _isNewUserState.send(NewUserState(isNewUser = true))
                    } else {
                        _isNewUserState.send(NewUserState(fail = "Oops! Something went wrong."))
                    }
                }
            }
        }
    }

    suspend fun loginWithEmailAndPassword(email: String, password: String) {
        authRepository.loginWithEmailAndPassword(email, password).collect {
            handleAuthResult(it)
        }
    }

    suspend fun registerWithEmailAndPassword(email: String, password: String) {
        authRepository.registerWithEmailAndPassword(email, password).collect {
            handleAuthResult(it)
        }
    }

    suspend fun resetPassword(email: String) {
        authRepository.resetPassword(email).collect {
            handleAuthResult(it)
        }
    }

    suspend fun handleActivityResult(activityResult: ActivityResult) {
        val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
        try {
            val result = account.getResult(ApiException::class.java)
            val credentials = GoogleAuthProvider.getCredential(result.idToken!!, null)
            loginWithCredentials(credentials)
        } catch (it: ApiException) {
            Log.e(TAG, "${it.message}")
        }
    }

    private suspend fun loginWithCredentials(credential: AuthCredential) {
        authRepository.loginWithCredentials(credentials = credential).collect {
            handleAuthResult(it)
        }
    }

    private suspend fun handleAuthResult(result: Resource<Int>) {
        when(result) {
            is Resource.Loading -> {
                _authState.send(AuthState(isLoading = true))
            }
            is Resource.Success -> {
                _authState.send(AuthState(success = result.data))
            }
            is Resource.Error -> {
                _authState.send(AuthState(fail = result.e?.message))
            }
        }
    }

    fun googleLoginOnClick(context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constant.WEB_CLIENT_ID)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }

    fun isUserLoggedIn(): Boolean {
        return authRepository.isLoggedIn()
    }
}