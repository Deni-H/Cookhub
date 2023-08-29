package com.deni.hilhamsyah.cookhub.ui.auth_screen.register_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar

@Composable
fun RegisterScreen(
    navController: NavController
) {
    CustomAppBar(navController = navController)
    Text(text = "Register Screen")
}