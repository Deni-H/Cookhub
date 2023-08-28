package com.deni.hilhamsyah.cookhub.ui.forget_password

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar

@Composable
fun ForgetPasswordScreen(
    navController: NavController
) {
    CustomAppBar(navController = navController)
    Text(text = "Forget Password Screen")
}