package com.deni.hilhamsyah.cookhub.ui.login_screen

import android.widget.Toast
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.deni.hilhamsyah.cookhub.R
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar

@Composable
fun LoginScreen(
    navController: NavController
) {
    val context = LocalContext.current
    CustomAppBar(
        navController = navController,
        title = "Hi, Welcome back!",
        content = {
            IconButton(onClick = { Toast.makeText(context, "Hello world", Toast.LENGTH_LONG).show() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_more),
                    contentDescription = "ic_back_arrow"
                )
            }
        }
    )
}