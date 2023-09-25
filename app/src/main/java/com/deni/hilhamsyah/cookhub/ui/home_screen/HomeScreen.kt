package com.deni.hilhamsyah.cookhub.ui.home_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar
import com.deni.hilhamsyah.cookhub.ui.components.CustomProgressDialog
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    val userProfileState = homeViewModel.userProfileState.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        coroutine.launch {
            homeViewModel.getUserProfile()
        }
    }

    if (userProfileState.value?.isLoading == true) {
        CustomProgressDialog()
    }

    if (userProfileState.value?.fail != null) {
        Toast.makeText(context, userProfileState.value?.fail, Toast.LENGTH_LONG).show()
    }

    Column(modifier = Modifier.padding(20.dp)) {
        CustomAppBar(title = "Home Screen")
        Spacer(modifier = Modifier.height(20.dp))
        if (userProfileState.value?.user != null) {
            val user = userProfileState.value?.user!!
            Text(text = "Email: ${user.email ?: "N/A"}")
            Text(text = "First name: ${user.firstName ?: "N/A"}")
            Text(text = "Last name: ${user.lastName ?: "N/A"}")
            Text(text = "Username: ${user.userName?.value ?: "N/A"}")
            Text(text = "Bio: ${user.bio ?: "N/A"}")
        }
    }
}