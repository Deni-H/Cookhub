package com.deni.hilhamsyah.cookhub.ui.home_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar
import com.deni.hilhamsyah.cookhub.ui.components.CustomProgressDialog
import com.deni.hilhamsyah.cookhub.ui.components.ProfileImage
import com.deni.hilhamsyah.cookhub.util.TAG
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    val userProfileState = homeViewModel.userProfileState.collectAsState(initial = null)
    val getImageProfileState = homeViewModel.getImageProfileState.collectAsState(initial = null)

    var profileUrl by rememberSaveable { mutableStateOf("") }
    var profileImage: ByteArray? by rememberSaveable { mutableStateOf(null) }


    LaunchedEffect(getImageProfileState.value?.image) {
        val imageByte = getImageProfileState.value?.image
        if (imageByte != null) {
            Log.d(TAG, "HomeScreen: Not null")
            profileImage = imageByte
        }
    }

    LaunchedEffect(profileUrl) {
        if (profileUrl.isNotEmpty()) {
            coroutine.launch {
                Log.d(TAG, "HomeScreen: Not empty")
                homeViewModel.getProfileImage(profileUrl)
            }
        }
    }

    LaunchedEffect(Unit) {
        coroutine.launch {
            Log.d(TAG, "HomeScreen: Launched from HomeScreen")
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
            profileUrl = user.profileImage!!
            Text(text = "Email: ${user.email}")
            Text(text = "Profile image: ${user.profileImage}")
            Text(text = "First name: ${user.firstName}")
            Text(text = "Last name: ${user.lastName}")
            Text(text = "Username: ${user.userName?.value}")
            Text(text = "Bio: ${user.bio}")
            Spacer(modifier = Modifier.height(20.dp))
            if (profileImage != null) {
                Column(modifier = Modifier.size(150.dp)) {
                    ProfileImage(model = profileImage)
                }
            }
            
            Button(onClick = { Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show() }) {
                Text(text = "Click me")
            }
        }
    }
}