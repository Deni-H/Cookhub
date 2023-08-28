package com.deni.hilhamsyah.cookhub.ui.home_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar

@Composable
fun HomeScreen(
    navController: NavController
) {
    CustomAppBar(navController = navController)
    Text(text = "Home Screen")
}