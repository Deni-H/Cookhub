package com.deni.hilhamsyah.cookhub.ui.auth_screen.login_screen

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import com.deni.hilhamsyah.cookhub.util.Constant
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginViewModel : ViewModel() {

    fun googleLoginOnClick(context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constant.WEB_CLIENT_ID)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }
}