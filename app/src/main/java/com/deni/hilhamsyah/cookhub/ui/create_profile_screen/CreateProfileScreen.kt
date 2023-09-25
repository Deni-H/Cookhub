package com.deni.hilhamsyah.cookhub.ui.create_profile_screen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deni.hilhamsyah.cookhub.domain.model.User
import com.deni.hilhamsyah.cookhub.navigation.Screen
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar
import com.deni.hilhamsyah.cookhub.ui.components.CustomProgressDialog
import com.deni.hilhamsyah.cookhub.ui.components.CustomTextField
import com.deni.hilhamsyah.cookhub.ui.components.ProfileImage
import com.deni.hilhamsyah.cookhub.ui.theme.CookhubTheme
import com.deni.hilhamsyah.cookhub.ui.theme.neutral50
import com.deni.hilhamsyah.cookhub.util.InputValidator
import com.deni.hilhamsyah.cookhub.util.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CreateProfileScreen(
    navController: NavController,
    createProfileViewModel: CreateProfileViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val createProfileState = createProfileViewModel.addProfileState.collectAsState(initial = null)
    val checkUsernameState = createProfileViewModel.checkUsernameState.collectAsState(initial = null)
    val setUserNameState = createProfileViewModel.setUserNameState.collectAsState(initial = null)

    var profileUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    var bio by rememberSaveable { mutableStateOf("") }

    var firstNameErrMsg: String? by rememberSaveable { mutableStateOf(null) }
    var lastNameErrMsg: String? by rememberSaveable { mutableStateOf(null) }
    var userNameErrMsg: String? by rememberSaveable { mutableStateOf(null) }
    var bioErrMsg: String? by rememberSaveable { mutableStateOf(null) }

    var checkUserNameJob by remember { mutableStateOf<Job?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        profileUri = it
    }

    if (createProfileState.value?.isLoading == true || setUserNameState.value?.isLoading == true) {
        CustomProgressDialog()
    }

    LaunchedEffect(createProfileState.value?.success) {
        if(createProfileState.value?.success != null) {
            Log.d(TAG, "CreateProfileScreen: create profile success")
            coroutineScope.launch {
                createProfileViewModel.setUserName(userName)
            }
        }
    }

    LaunchedEffect(createProfileState.value?.fail) {
        if (createProfileState.value?.fail != null) {
            Toast.makeText(context, createProfileState.value?.fail, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(setUserNameState.value?.success) {
        if(setUserNameState.value?.success != null) {
            Log.d(TAG, "CreateProfileScreen: setUserName success")
            navController.popBackStack()
            navController.navigate(Screen.HomeScreen.route)
        }
    }

    LaunchedEffect(setUserNameState.value?.fail) {
        if (setUserNameState.value?.fail != null) {
            Toast.makeText(context, setUserNameState.value?.fail, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(checkUsernameState.value?.success) {
        if(checkUsernameState.value?.success != null) {
            userNameErrMsg = null
        }
    }

    LaunchedEffect(checkUsernameState.value?.fail) {
        if(checkUsernameState.value?.fail != null) {
            userNameErrMsg = checkUsernameState.value?.fail
        }
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomAppBar(title = "Setup profile")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Don't worry, only you can see your personal data. No one else will be able to see it.",
            color = neutral50
        )
        Spacer(modifier = Modifier.height(50.dp))

        Column(modifier = Modifier.size(150.dp)) {
            ProfileImage(
                model = profileUri,
                showFileChooser = true,
                chooseFileOnclick = { launcher.launch("image/*") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstName,
            onValueChange = {
                firstName = it
                firstNameErrMsg = InputValidator.maxCharValidator(it, 50)
            },
            placeholder = "First Name",
            errorMessage = firstNameErrMsg,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            onValueChange = {
                lastName = it
                lastNameErrMsg = InputValidator.maxCharValidator(it, 50)
            },
            placeholder = "Last Name",
            errorMessage = lastNameErrMsg,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = {
                userName = it
                checkUserNameJob?.cancel()
                userNameErrMsg = InputValidator.userNameValidator(it)

                if (userNameErrMsg == null) {
                    checkUserNameJob = coroutineScope.launch {
                        delay(2000)
                        createProfileViewModel.isUserNameExists(userName)
                    }
                }
            },
            placeholder = "Username",
            errorMessage = userNameErrMsg,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = bio,
            onValueChange = {
                bio = it
                bioErrMsg = InputValidator.maxCharValidator(it, 100)
            },
            placeholder = "Bio",
            errorMessage = bioErrMsg,
            singleLine = false,
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                coroutineScope.launch(Dispatchers.Main) {
                    createProfileViewModel.addUserProfile(User(
                        firstName = firstName,
                        lastName = lastName,
                        profileImage = profileUri.toString(),
                        bio = bio
                    ))
                }
            },
            enabled = firstNameErrMsg == null && firstName.isNotEmpty() &&
                      userNameErrMsg == null && userName.isNotEmpty() &&
                      lastNameErrMsg == null &&
                      bioErrMsg == null
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateProfileScreenPreview() {
    CookhubTheme {
        CreateProfileScreen(navController = rememberNavController())
    }
}