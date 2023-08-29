package com.deni.hilhamsyah.cookhub.ui.auth_screen.register_screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deni.hilhamsyah.cookhub.R
import com.deni.hilhamsyah.cookhub.navigation.Screen
import com.deni.hilhamsyah.cookhub.ui.auth_screen.AuthViewModel
import com.deni.hilhamsyah.cookhub.ui.components.CustomAppBar
import com.deni.hilhamsyah.cookhub.ui.components.CustomProgressDialog
import com.deni.hilhamsyah.cookhub.ui.components.CustomTextField
import com.deni.hilhamsyah.cookhub.ui.theme.CookhubTheme
import com.deni.hilhamsyah.cookhub.ui.theme.neutral50
import com.deni.hilhamsyah.cookhub.util.Constant
import com.deni.hilhamsyah.cookhub.util.InputValidator
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var emailErrMsg: String? by rememberSaveable { mutableStateOf(null) }
    var passErrMsg: String? by rememberSaveable { mutableStateOf(null) }
    var passwordVisibility by rememberSaveable { mutableStateOf(true) }
    var confirmPassErrMsg: String? by rememberSaveable { mutableStateOf(null) }

    val authState = authViewModel.authState.collectAsState(initial = null)

    val icon =
        if (passwordVisibility) ImageVector.vectorResource(R.drawable.ic_eye_open)
        else ImageVector.vectorResource(R.drawable.ic_eye_closed)

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken!!, null)
                coroutineScope.launch {
                    authViewModel.signInWithCredentials(credentials)
                }
            } catch (it: ApiException) {
                println("Error: ${it.message}")
                println(it)
            }
        }

    if (authState.value?.isLoading == true) {
        CustomProgressDialog()
    }

    LaunchedEffect(key1 = authState.value?.success) {
        if(authState.value?.success != null) {
            Toast.makeText(context, authState.value?.success, Toast.LENGTH_LONG).show()
            navController.popBackStack()
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.OnboardingScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = authState.value?.fail) {
        if (authState.value?.fail != null) {
            Toast.makeText(context, authState.value?.fail, Toast.LENGTH_LONG).show()
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        val (
            topBarRef,
            descriptionRef,
            inputContainerRef,
            regisBtnRef,
            dividerRef,
            googleBtnRef,
            footerRef
        ) = createRefs()

        CustomAppBar(
            modifier = Modifier
                .constrainAs(topBarRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            navController = navController,
            title = "Create new account"
        )
        Text(
            modifier = Modifier
                .constrainAs(descriptionRef) {
                    top.linkTo(topBarRef.bottom, 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = "Get started with a new account and unlock a world of delicious recipes and culinary inspiration!",
            color = neutral50
        )

        Column(
            modifier = Modifier
                .constrainAs(inputContainerRef) {
                    top.linkTo(
                        anchor = descriptionRef.bottom,
                        margin = 30.dp
                    )
                }
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                    emailErrMsg = InputValidator.validateEmail(it)
                },
                placeholder = "Email",
                errorMessage = emailErrMsg,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            )

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                    passErrMsg = InputValidator.validatePassword(it)
                },
                placeholder = "Password",
                errorMessage = passErrMsg,
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "ic_eye",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                isPassword = passwordVisibility,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
            )

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPassErrMsg = if (confirmPassword != password) "Password doesn't match" else null
                },
                placeholder = "Confirm password",
                errorMessage = confirmPassErrMsg,
                isPassword = passwordVisibility,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
            )
        }

        Button(
            modifier = Modifier
                .constrainAs(regisBtnRef) {
                    top.linkTo(
                        anchor = inputContainerRef.bottom,
                        margin = 50.dp
                    )
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                coroutineScope.launch {
                    authViewModel.registerWithEmailAndPassword(email, password)
                }
            },
            enabled =
                emailErrMsg == null &&
                passErrMsg == null &&
                confirmPassErrMsg == null &&
                email.isNotEmpty() &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty()
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }

        Row(
            modifier = Modifier.constrainAs(dividerRef) {
                top.linkTo(
                    anchor = regisBtnRef.bottom,
                    margin = 50.dp
                )
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 2.dp
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "Or login with",
                fontWeight = FontWeight(600),
                color = Color(0xFF6A707C),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Divider(
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 2.dp
            )
        }

        Button(
            modifier = Modifier
                .constrainAs(googleBtnRef) {
                    top.linkTo(
                        anchor = dividerRef.bottom,
                        margin = 50.dp
                    )
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            onClick = {
                val gso = GoogleSignInOptions
                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(Constant.WEB_CLIENT_ID)
                    .requestEmail()
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_google),
                contentDescription = "null",
                tint = Color.Unspecified
            )
        }

        Text(
            modifier = Modifier.constrainAs(footerRef) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = buildAnnotatedString {
                append("Read")
                withStyle(style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
                ) {
                    append(" privacy ")
                }
                append("and")
                withStyle(style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
                ) {
                    append(" policy")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    CookhubTheme {
        RegisterScreen(navController = rememberNavController())
    }
}